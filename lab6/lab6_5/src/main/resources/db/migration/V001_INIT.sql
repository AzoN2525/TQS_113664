CREATE TABLE cars (
  id BIGSERIAL PRIMARY KEY,
  make VARCHAR(100) NOT NULL,
  model VARCHAR(100) NOT NULL
);

INSERT INTO cars (make, model) VALUES ('Toyota', 'Corolla'), ('Honda', 'Civic');