INSERT INTO librarian (id, username, password, role)
VALUES (1, 'admin', '$2y$10$HmWU9Hk2smNYZReMGWdGpOe3BEUyaeJKzkUsJR7T.uu.J3r6sY70K', 'ROLE_ADMIN')
ON DUPLICATE KEY UPDATE username = 'admin', role = 'ADMIN';