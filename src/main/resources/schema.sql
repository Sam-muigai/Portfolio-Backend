
CREATE TABLE IF NOT EXISTS user_table (
    id SERIAL PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    current_user_role VARCHAR(255) NOT NULL,
    profile_picture_link TEXT
);

CREATE TABLE IF NOT EXISTS projects(
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    image_url TEXT,
    project_url TEXT NOT NULL,
    user_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_table(id)
);







