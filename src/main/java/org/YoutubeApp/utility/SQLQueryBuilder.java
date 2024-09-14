package org.YoutubeApp.utility;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class SQLQueryBuilder {
	
	public static String generateInsert(Object entity, String tableName) {
		Class<?> clazz = entity.getClass();
		Field[] fields = clazz.getDeclaredFields();
		StringBuilder columns = new StringBuilder();
		StringBuilder values = new StringBuilder();
		
		boolean firstField = true;
		for (Field field : fields) {
			field.setAccessible(true);
			if (!field.getName().equalsIgnoreCase("id")) {
				if (!firstField) {
					columns.append(", ");
					values.append(", ");
				} else {
					firstField = false;
				}
				columns.append(field.getName());
				try {
					Object value = field.get(entity);
					if (value == null) {
						values.append("NULL");
					}  else if (value instanceof String || value instanceof Date || value instanceof LocalDate || value instanceof Timestamp || value instanceof Enum<?>) {
						values.append("'").append(value).append("'");
					} else {
						values.append(value);
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + values + ")";
	}
	
	public static String generateUpdate(Object entity, String tableName) {
		Class<?> clazz = entity.getClass();
		Field[] fields = clazz.getDeclaredFields();
		StringBuilder setClause = new StringBuilder();
		Object idValue = null;
		
		for (Field field : fields) {
			field.setAccessible(true);
			if (field.getName().equalsIgnoreCase("id")) {
				try {
					idValue = field.get(entity);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			} else {
				try {
					Object value = field.get(entity);
					if (setClause.length() > 0) setClause.append(", ");
					setClause.append(field.getName()).append("=");
					if (value == null) {
						setClause.append("NULL");
					} else if (value instanceof String || value instanceof Date || value instanceof LocalDate || value instanceof Timestamp) {
						setClause.append("'").append(value).append("'");
					} else {
						setClause.append(value);
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		if (idValue == null) {
			throw new IllegalArgumentException("ID yok.");
		}
		
		return "UPDATE " + tableName + " SET " + setClause + " WHERE id=" + idValue;
	}
	
	public static String generateDelete(Class<?> entityClass, String tableName, Object id) {
		String idColumnName = "id";
		String sql = "DELETE FROM " + tableName + " WHERE " + idColumnName + " = '" + id + "'";
		return sql;
	}
	
	public static <T> List<T> generateList(Class<T> entityClass, String tableName, ResultSet resultSet) {
		List<T> resultList = new ArrayList<>();
		try {
			while (resultSet.next()) {
				T entity = entityClass.getDeclaredConstructor().newInstance();
				for (Field field : entityClass.getDeclaredFields()) {
					field.setAccessible(true);
					String fieldName = field.getName();
					Object value = resultSet.getObject(fieldName);
					
					if (value != null) {
						if (field.getType().isEnum()) {
							Class<Enum> enumType = (Class<Enum>) field.getType();
							value = Enum.valueOf(enumType, value.toString());
						}
						field.set(entity, value);
					}
				}
				resultList.add(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}
	
	
	public static <T> Optional<T> findById(Class<T> entityClass, String tableName, Object id, ResultSet resultSet) {
		String idColumnName = "id";
		String sql = "SELECT * FROM " + tableName + " WHERE " + idColumnName + " = '" + id + "'";
		try {
			if (resultSet.next()) {
				T entity = entityClass.getDeclaredConstructor().newInstance();
				for (Field field : entityClass.getDeclaredFields()) {
					field.setAccessible(true);
					String fieldName = field.getName();
					Object value = resultSet.getObject(fieldName);
					
					if (value != null) {
						if (field.getType().isEnum()) {
							Class<Enum> enumType = (Class<Enum>) field.getType();
							value = Enum.valueOf(enumType, value.toString());
						}
						field.set(entity, value);
					}
				}
				return Optional.of(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Optional.empty();
	}
	
}