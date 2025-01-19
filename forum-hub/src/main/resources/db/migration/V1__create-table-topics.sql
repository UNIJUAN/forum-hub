CREATE TABLE topics (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        title VARCHAR(100) NOT NULL,
                        message TEXT NOT NULL,
                        creation_date TIMESTAMP NOT NULL,
                        status VARCHAR(20) NOT NULL,
                        author VARCHAR(100) NOT NULL,
                        course VARCHAR(100) NOT NULL,
                        active BOOLEAN NOT NULL
);