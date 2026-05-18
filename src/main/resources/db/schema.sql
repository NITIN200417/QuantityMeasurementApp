CREATE TABLE IF NOT EXISTS measurement (
    id INT AUTO_INCREMENT PRIMARY KEY,

    operation VARCHAR(50),

    value1 DOUBLE,

    value2 DOUBLE,

    result BOOLEAN
);