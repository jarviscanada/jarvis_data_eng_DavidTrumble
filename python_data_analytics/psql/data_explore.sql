-- Show table schema 
\d+ retail;


-- Q1: Show first 10 rows
SELECT * 
FROM retail 
limit 10;

-- Q2: Check # of records
SELECT COUNT(*) 
FROM retail;


-- Q3: number of clients 
SELECT COUNT(DISTINCT customer_id) 
FROM retail;


-- Q4: invoice date range
SELECT MAX(invoice_date), MIN(invoice_date) 
FROM retail;


-- Q5: number of SKU/merchants 
SELECT COUNT(DISTINCT stock_code) 
FROM retail;


-- Q6: Calculate average invoice amount excluding invoices with a negative amount
SELECT AVG(invoiceAmount)
FROM (
	SELECT SUM(unit_price * quantity) AS invoiceAmount 
	FROM retail
	GROUP BY invoice_no
	HAVING invoiceAmount > 0
);


-- Q7: Calculate total revenue
SELECT SUM(quantity * unit_price) 
FROM retail;


-- Q8: Calculate total revenue by YYYYMM 
