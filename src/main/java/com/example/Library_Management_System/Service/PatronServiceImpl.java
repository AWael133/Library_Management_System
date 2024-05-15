package com.example.Library_Management_System.Service;

import com.example.Library_Management_System.Entity.Patron;
import com.example.Library_Management_System.Exceptions.EntityNotFoundException;
import com.example.Library_Management_System.Repository.PatronRepository;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PatronServiceImpl implements PatronService{

    private final CacheManager cacheManager;
    final String PATRON_INFO_CACHE = "patronInfo";

    String model = "Patron";

    private PatronRepository patronRepository;

    public PatronServiceImpl(CacheManager cacheManager,
                             PatronRepository patronRepository){
        this.cacheManager = cacheManager;
        this.patronRepository = patronRepository;
    }
    @Override
    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    @Override
    @Cacheable(value=PATRON_INFO_CACHE, key="#id")
    public Patron getPatronById(Long id) {
        return patronRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(model));
    }

    @Override
    public Patron addPatron(Patron patron) {
        patron.setId(null);
        if(patronRepository.existsByName(patron.getName()))
            throw new RuntimeException("name already exists");
        patron = patronRepository.save(patron);

        Cache patronInfoCache = cacheManager.getCache(PATRON_INFO_CACHE);
        if(patronInfoCache != null)
            patronInfoCache.put(patron.getId(), patron);
        return patron;
    }

    @Override
    @CachePut(value=PATRON_INFO_CACHE, key="#id")
    public Patron editPatron(Long id, Patron patron) {
        Patron patron0 = patronRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(model));
        patron.setId(patron0.getId());

        if(patronRepository.existsByNameAndIdNot(patron.getName(), patron0.getId()))
            throw new RuntimeException("name already exists");
        return patronRepository.save(patron);
    }

    @Override
    @Transactional
    public void deletePatron(Long id) {
        Patron patron = patronRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(model));
        patronRepository.delete(patron);

        Cache patronInfoCache = cacheManager.getCache(PATRON_INFO_CACHE);
        if(patronInfoCache != null)
            patronInfoCache.evictIfPresent(id);
    }
}
