package com.example.tickerchart;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

public class TickerChartAssignment {

	private static final String FILE_NAME = "/home/feras/eclipse-workspace/LogAnalyzer/src/com/example/uniticker/trackFile2015-03-08.log";
	private static final String JOPTION_PANE_MESSAGE = "How much operation did you want?\nEnter any non integer value to return all operations.";

	private static List<Map.Entry<String, Integer>> sortMapInDescOrder(Map<String, Integer> map) {
		List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		return list;
	}

	private static int showInputDialog() {
		try {
			return Integer.parseInt(JOptionPane.showInputDialog(JOPTION_PANE_MESSAGE));
		} catch (Exception e) {
			return -1;
		}
	}

	private static void showResult(Map<String, Integer> operationCounts, int usersCount) {
		List<Map.Entry<String, Integer>> list = sortMapInDescOrder(operationCounts);
		int numberOfOperations = showInputDialog();

		if (numberOfOperations < 1) {
			numberOfOperations = list.size();
		}

		for (int index = 0; index < numberOfOperations; index++) {
			String operationName = list.get(index).getKey();
			int operationValue = list.get(index).getValue();

			System.out.printf("%s is used by %.2f%% of users\n", operationName,
					(double) operationValue / usersCount * 100);
		}
	}

	private static void readLogFile(Map<String, Integer> operationCounts, Set<String> users) {
		Set<String> userOperations = new HashSet<String>();

		try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] fields = line.split("/");
				String user = fields[1].trim();
				String operation = fields[2].trim();
				String userOperation = user + "*" + operation;

				if (!userOperations.contains(userOperation)) {
					operationCounts.put(operation, operationCounts.getOrDefault(operation, 0) + 1);
				}

				users.add(user);
				userOperations.add(userOperation);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Map<String, Integer> operationCounts = new HashMap<>();
		Set<String> users = new HashSet<String>();

		readLogFile(operationCounts, users);
		showResult(operationCounts, users.size());

	}

}
