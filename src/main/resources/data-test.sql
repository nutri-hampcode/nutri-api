-- Crear la tabla 'goal' si no existe
CREATE TABLE IF NOT EXISTS goal (
                                    id SERIAL PRIMARY KEY,
                                    name VARCHAR(50) NOT NULL
);

-- Crear la tabla 'exercise' si no existe
CREATE TABLE IF NOT EXISTS exercise (
                                        id SERIAL PRIMARY KEY,
                                        link_video VARCHAR(255) UNIQUE NOT NULL,
                                        image BYTEA,
                                        description TEXT NOT NULL,
                                        id_goal INTEGER NOT NULL,
                                        CONSTRAINT FK_goal FOREIGN KEY (id_goal) REFERENCES goal(id) ON DELETE CASCADE
);

-- Crear la tabla 'tips' si no existe
CREATE TABLE IF NOT EXISTS tip (
                                    id SERIAL PRIMARY KEY,
                                    content TEXT NOT NULL,
                                    id_exercise INTEGER NOT NULL,
                                    CONSTRAINT FK_exercise FOREIGN KEY (id_exercise) REFERENCES exercise(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS diet_types (
                                          id SERIAL PRIMARY KEY,
                                          type VARCHAR(50) NOT NULL,
                                          description TEXT NOT NULL
);

-- Crear la tabla 'user2' si no existe
CREATE TABLE IF NOT EXISTS user2 (
                                     id SERIAL PRIMARY KEY,
                                     name VARCHAR(100) NOT NULL,
                                     username VARCHAR(50) NOT NULL,
                                     password VARCHAR(255) NOT NULL,
                                     email VARCHAR(100) NOT NULL,
                                     height REAL,
                                     weight REAL,
                                     age INTEGER,
                                     allergies VARCHAR(20),
                                     id_goal INTEGER,
                                     id_diet_type INTEGER,
                                     CONSTRAINT UQ_username_email UNIQUE (username, email),
                                     CONSTRAINT FK_id_goal FOREIGN KEY (id_goal) REFERENCES goal(id),
                                     CONSTRAINT FK_id_diet_types FOREIGN KEY (id_diet_type) REFERENCES diet_types(id)
);

-- Crear la tabla 'feedback' si no existe
CREATE TABLE IF NOT EXISTS feedback (
                                        id SERIAL PRIMARY KEY,
                                        title VARCHAR(100) NOT NULL,
                                        description TEXT NOT NULL,
                                        id_user INTEGER NOT NULL,
                                        CONSTRAINT FK_user FOREIGN KEY (id_user) REFERENCES user2(id)
);

-- Insertar datos en la tabla 'goal'
INSERT INTO goal (id, name) VALUES
                                (1, 'BAJAR_PESO'),
                                (2, 'SUBIR_PESO'),
                                (3, 'MANTENER_PESO')
ON CONFLICT DO NOTHING;

-- Insertar datos en la tabla 'exercise'
INSERT INTO exercise (id, link_video, image, description, id_goal) VALUES
                                                                       (1, 'https://linkvideo1.com', NULL, 'Ejercicio para quemar grasa', 1),
                                                                       (2, 'https://linkvideo2.com', NULL, 'Ejercicio para ganar masa muscular', 2),
                                                                       (3, 'https://linkvideo3.com', NULL, 'Ejercicio para mantener el peso', 3)
ON CONFLICT DO NOTHING;

-- Insertar datos en la tabla 'tip'
INSERT INTO tip (id, content, id_exercise) VALUES
                                                (1, 'Mantén una buena hidratación durante este ejercicio', 1),
                                                (2, 'Realiza este ejercicio con pesas ligeras para evitar lesiones', 2),
                                                (3, 'Haz este ejercicio 3 veces por semana para mantener el peso', 3),
                                                (4, 'Asegúrate de calentar antes de comenzar el ejercicio', 1),
                                                (5, 'Descansa entre las series para maximizar el crecimiento muscular', 2)
ON CONFLICT DO NOTHING;

-- Insertar datos en la tabla 'diet_types'
INSERT INTO diet_types (id, type, description) VALUES
                                                   (1, 'VEGETARIAN', 'A diet that excludes meat and fish, but may include dairy products and eggs.'),
                                                   (2, 'VEGAN', 'A diet that excludes all animal products, including dairy and eggs.'),
                                                   (3, 'KETO', 'A high-fat, adequate-protein, low-carbohydrate diet.'),
                                                   (4, 'PALEO', 'A diet based on foods similar to what might have been eaten during the Paleolithic era.')
ON CONFLICT DO NOTHING;

-- Insertar datos en la tabla 'user2'
INSERT INTO user2 (name, username, password, email, height, weight, age, allergies, id_goal, id_diet_type) VALUES
                                                                                                               ('John Doe', 'johndoe', 'password123', 'john@example.com', 180.5, 75.0, 30, 'NONE', 1, 1),
                                                                                                               ('Jane Smith', 'janesmith', 'password456', 'jane@example.com', 165.0, 60.0, 28, 'GLUTEN', 2, 2),
                                                                                                               ('Bob Johnson', 'bobjohnson', 'password789', 'bob@example.com', 175.0, 80.0, 35, 'DAIRY', 3, 3)
ON CONFLICT DO NOTHING;

-- Insertar datos en la tabla 'feedback'
INSERT INTO feedback (title, description, id_user) VALUES
                                                       ('Great app!', 'I love using this app for my fitness journey.', 1),
                                                       ('Needs improvement', 'The diet recommendations could be more diverse.', 2),
                                                       ('Fantastic progress', 'I''ve seen great results using the exercise plans.', 3)
ON CONFLICT DO NOTHING;

