INSERT INTO admins (username, password) 
VALUES ('admin', 'admin123')
ON CONFLICT DO NOTHING;
