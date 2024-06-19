insert into transaction(trans_date, amount, note) values
            ('2024-07-05','50','gepuk');

INSERT INTO budget (period, start_date, end_date, budget_amount) VALUES
('Monthly', '2024-06-01', '2024-06-30', 1000),
('Weekly', '2024-06-01', '2024-06-07', 200),
('Monthly', '2024-07-01', '2024-07-31', 1500);

update transaction set budget_id = 2
where trans_id = 1

