package com.example.demo.config;

/**
 * コンディションIDの定数クラス群
 * 
 * @author moriharanariki
 *
 */
public enum Condition {

	/* 新品・未使用品 */
	MINT("mint", 1),
	/* 未使用品に近い */
	NEAR_MINT("nearMint", 2),
	/* 良い */
	GOOD("good", 3),
	/* 悪い */
	BAD("bad", 4);

	private final String key;
	private final int value;

	/**
	 * コンディションIDから一致するインスタンスを返す.
	 * 
	 * @param id コンディションID
	 * @return コンディション
	 */
	public static Condition getByValue(int id) {
		for (Condition condition : Condition.values()) {
			if (condition.getValue() == id) {
				return condition;
			}
		}
		return null;
	}

	private Condition(final String key, final int value) {
		this.key = key;
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public String getKey() {
		return key;
	}
}
