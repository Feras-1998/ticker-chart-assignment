# TickerChart
 
Notes:
	1. I use Java 17.
	2. I use JOptionPane from javax.swing library, if you want to run this code in online compiler you may have some issue.
	
Solution:
	
	Problem 1:
		Please run the main method in TickerChartAssignment class.
		
	Problem 2:
		Manually, i choose some random operations and check it manually.
		
	Problem 3:
		When you run the project a dialog will demand to enter number of operations you want, if you enter a non numeric value the program will print all the operations.
		
	Problem 4:
		We can choose one of these solutions based on the end user requirements: 

			1. Store the log in JSON format, because read file line by line take more than 80% of the time. 

			2. Use cron job (cron job in Linux and task scheduler in windows) to analyse the data and store the data in local database or new file, when the user ask for the operations analysis the system will read from the local database or file we store in.
			Note: This solution NOT QUITE ACCURATE because we have one minute gape.
			
			3. Rather store the data in log file we can use local database because it will be more flexible and more fast from read data from log file.
