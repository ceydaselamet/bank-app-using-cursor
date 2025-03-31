-- Insert default roles if they don't exist
INSERT INTO roles (id, name, description, created_at) 
SELECT 1, 'ROLE_USER', 'Standard user role', CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'ROLE_USER');

INSERT INTO roles (id, name, description, created_at) 
SELECT 2, 'ROLE_ADMIN', 'Administrator role', CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'ROLE_ADMIN'); 