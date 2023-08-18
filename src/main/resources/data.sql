INSERT INTO customers (full_name, contact) VALUES
            ('John Doe', '097 XXX-XX'),
            ('Jane Smith', '098 XXX-XX');

INSERT INTO orders (customer_id, additional_information, status, date) VALUES
      (1, 'Awaiting details', 'Waiting', CURRENT_DATE),
      (2, 'In progress', 'In work', CURRENT_DATE);