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
