package com.example.demo.validation;

import org.springframework.beans.BeanWrapperImpl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

//ConstraintValidator の実装クラスとして作成する
//<>内の引数1つ目はアノテーションクラス名、2つ目はObject.
public class NotBlankAnyValidator implements ConstraintValidator<ValidateItemType, Object> {

	// バリデーション対象の変数の値を入れる
	private String[] fields;

	// アノテーションクラスで設定しているエラーメッセージが入る
	private String message;

	/**
	 * 初期化処理 ()内のクラスはアノテーションクラスが入る.
	 */
	@Override
	public void initialize(ValidateItemType constraintAnnotation) {
		this.fields = constraintAnnotation.fields(); // ※1
		// アノテーションで記述したデフォルトメッセージを追加.
		message = constraintAnnotation.message();
	}

	/**
	 * 自分でバリデーション内容を設定する
	 */
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		// BeanWrapperImpleはSetter,Getterを使わずに、プロパティの値を設定したり取得したりするAPI.
		BeanWrapperImpl beanWrapper = new BeanWrapperImpl(value);
		int count = 0;

		for (String string : fields) {
			// 以下で、valueに、validation対象の各値(今回の場合ItemDataで設定されているプロパティの値)が入る.
			// beanWrapper.getPropertyValue(string)で値を取得している.stringにはプロパティ名が入っている.
			value = beanWrapper.getPropertyValue(string);

			// 各項目で空もしくはnullの場合はエラーを出す.
			if (value == "" || value == null) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(message).addPropertyNode(string).addConstraintViolation();
				count += 1;
			}

		}

		// エラーが一つでも存在すればfalseを返す.
		if (count > 0) {
			return false;
		}
		// trueがないとエラー文なしのエラーが起きてしまう.必ず書く.
		return true;
	}
}
