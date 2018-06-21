package com.senpure.base.util;


public abstract class Assert {

	
	public static void error( String message) {
		
			throw new IllegalArgumentException(message);
		
	}
	
	
	public static void isTrue(boolean expression, String message) {

		if (!expression) {
			throw new IllegalArgumentException(message);
		}
	}
	
	public static void isTrue(boolean expression) {
		isTrue(expression, "[Assertion failed] - this expression must be true");
	}
	public static void isNull(Object object, String message) {
		if (object != null) {
			throw new IllegalArgumentException(message);
		}
	}
	public static void isNull(Object object) {
		isNull(object, "[Assertion failed] - the object argument must be null");
	}
	public static void noNullElements(Object[] array, String message) {
		if (array != null) {
			for (Object element : array) {
				if (element == null) {
					throw new IllegalArgumentException(message);
				}
			}
		}
	}

	public static void noNullElements(Object[] array) {
		noNullElements(array, "[Assertion failed] - this array must not contain any null elements");
	}

	public static void state(boolean expression, String message) {
		if (!expression) {
			throw new IllegalStateException(message);
		}
	}
	public static void state(boolean expression) {
		state(expression, "[Assertion failed] - this state invariant must be true");
	}
	public static void notNull(Object object, String message) {
		if(object == null) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void notNull(Object object) {
		notNull(object, "[Assertion failed] - this argument is required; it must not be null");
	}

}
