INSERT INTO librarian (id, username, password)
VALUES (1, 'admin', '$2y$10$HmWU9Hk2smNYZReMGWdGpOe3BEUyaeJKzkUsJR7T.uu.J3r6sY70K')
ON DUPLICATE KEY UPDATE username = 'admin';