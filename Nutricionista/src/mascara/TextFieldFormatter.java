package mascara;

import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.text.MaskFormatter;

import javafx.scene.control.TextField;

public class TextFieldFormatter extends TextField {
	private final MaskFormatter mf;
	private TextField tf;
	private String CaracteresValidos;
	private String mask;
	private ArrayList<String> patterns;

	public TextFieldFormatter() {
		mf = new MaskFormatter();
		patterns = new ArrayList<String>();
	}

	public void formatter(TextField tf, String CaracteresValidos, String mask) {
		try {
			mf.setMask(mask);
		} catch (ParseException ex) {
			System.out.println(ex.getMessage());
		}

		mf.setValidCharacters(CaracteresValidos);
		mf.setValueContainsLiteralCharacters(false);
		String text = tf.getText().replaceAll("[\\W]", "");

		boolean repetir = true;
		while (repetir) {

			char ultimoCaractere;

			if (text.equals("")) {
				break;
			} else {
				ultimoCaractere = text.charAt(text.length() - 1);
			}

			try {
				text = mf.valueToString(text);
				repetir = false;
			} catch (ParseException ex) {
				text = text.replace(ultimoCaractere + "", "");
				repetir = true;
			}

		}

		tf.setText(text);

		if (!text.equals("")) {
			for (int i = 0; tf.getText().charAt(i) != ' ' && i < tf.getLength() - 1; i++) {
				tf.forward();
			}
		}
	}

	public void formatter() {
		formatter(this.tf, this.CaracteresValidos, this.mask);
	}

	/**
	 * @return the tf
	 */
	public TextField getTf() {
		return tf;
	}

	/**
	 * @param tf
	 *            the tf to set
	 */
	public void setTf(TextField tf) {
		this.tf = tf;
	}

	/**
	 * @return the CaracteresValidos
	 */
	public String getCaracteresValidos() {
		return CaracteresValidos;
	}

	/**
	 * @param CaracteresValidos
	 *            the CaracteresValidos to set
	 */
	public void setCaracteresValidos(String CaracteresValidos) {
		this.CaracteresValidos = CaracteresValidos;
	}

	public String getMask() {
		return mask;
	}

	/**
	 * @param mask
	 *            the mask to set
	 */
	public void setMask(String mask) {
		this.mask = mask;
	}

	public void setValidaCampo(String validaCampo) {
		String tempMask = "^";

		for (int i = 0; i < validaCampo.length(); ++i) {

			char temp = validaCampo.charAt(i);
			String regex;
			int counter = 1;
			int step = 0;

			if (i < validaCampo.length() - 1) {
				for (int j = i + 1; j < validaCampo.length(); ++j) {
					if (temp == validaCampo.charAt(j)) {
						++counter;
						step = j;
					} else if (validaCampo.charAt(j) == '!') {
						counter = -1;
						step = j;
					} else {
						break;
					}
				}
			}
			switch (temp) {

			case '*':
				regex = ".";
				break;
			case 'S':
				regex = "[^\\s]";
				break;
			case 'P':
				regex = "[A-z.]";
				break;
			case 'M':
				regex = "[0-z.]";
				break;
			case 'A':
				regex = "[0-z]";
				break;
			case 'N':
				regex = "[0-9]";
				break;
			case 'L':
				regex = "[A-z]";
				break;
			case 'U':
				regex = "[A-Z]";
				break;
			case 'l':
				regex = "[a-z]";
				break;
			case '.':
				regex = "\\.";
				break;
			default:
				regex = Character.toString(temp);
				break;

			}

			if (counter != 1) {

				this.patterns.add(regex);

				if (counter == -1) {
					regex += "+";
					this.patterns.add(regex);
				} else {
					String tempRegex = regex;
					for (int k = 1; k < counter; ++k) {
						regex += tempRegex;
						this.patterns.add(tempRegex);
					}
				}

				i = step;

			} else {
				this.patterns.add(regex);
			}

			tempMask += regex;

		}

		this.mask = tempMask + "$";

	}
}
