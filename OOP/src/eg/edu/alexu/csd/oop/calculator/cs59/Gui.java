package eg.edu.alexu.csd.oop.calculator.cs59;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import eg.edu.alexu.csd.oop.calculator.Calculator;


/**
 *
 * @author speedtech.
 *
 */
public class Gui extends JFrame implements MouseListener  {
	/**
	 * magic numbers.
	 */
	private final int magic84 = 84, magic100 = 100,
			magic10 = 10, magic30 = 30,
			magic190 = 190, magic280 = 280,
			magic370 = 370, magic355 = 355,
			magic450 = 450, magic385 = 385,
			magic500 = 500, magic485 = 485,
			magic200 = 200, magic150 = 150,
			magic250 = 250, magic300 = 300,
			magic105 = 105, magic90 = 90,
			magic125 = 125, magic240 = 240,
			magic80 = 80;
	/**
	 * my casio.
	 */
	private Calculator myCalc = new MyCalculator();
	/**
	 * Jtest field state.
	 */
	private boolean res = false;
	/**
	 * JTextField.
	 */
	private JTextField expression;
	/**
	 * Buttons.
	 */
	private JButton btn0, btn1, btn2,
	btn3, btn4, btn5, btn6, btnC,
	btn7, btn8, btn9, btnS, btnCr, btnCl,
	btnL, btnE, btnP, btnMi, btnCur,
	btnMul, btnDiv, btnDot, btnPrev, btnNext;
	/**
	 * GUI.
	 */
	public Gui() {
		setBounds(magic500, magic200, magic485, magic385);
		setTitle("My Casio");
		setLayout(null);
		expression = new JTextField();
		expression.setBackground(Color.white);
		expression.setForeground(Color.BLACK);
		expression.setText(null);
		expression.setBounds(magic10, magic10, magic450, magic80);
		btnL = new JButton("Load");
		btnL.setBounds(magic10, magic100, magic84, magic30);
		btnL.setBackground(Color.white);
		btnL.addMouseListener(this);
		btnPrev = new JButton("Previous");
		btnPrev.setBounds(magic100, magic100, magic84, magic30);
		btnPrev.setBackground(Color.white);
		btnPrev.addMouseListener(this);
		btnNext = new JButton("Next");
		btnNext.setBounds(magic280, magic100, magic84, magic30);
		btnNext.addMouseListener(this);
		btnNext.setBackground(Color.white);
		btnCur = new JButton("Current");
		btnCur.setBounds(magic190, magic100, magic84, magic30);
		btnCur.setBackground(Color.white);
		btnCur.addMouseListener(this);
		btnE = new JButton("=");
		btnE.setBounds(magic370, magic100, magic90, magic30);
		btnE.setBackground(Color.white);
		btnE.addMouseListener(this);
		btn7 = new JButton("7");
		btn7.setBounds(magic10, magic150, magic84, magic30);
		btn7.setBackground(Color.white);
		btn7.addMouseListener(this);
		btn8 = new JButton("8");
		btn8.setBounds(magic100, magic150, magic84, magic30);
		btn8.setBackground(Color.white);
		btn8.addMouseListener(this);
		btn9 = new JButton();
		btn9.setText("9");
		btn9.setBounds(magic190, magic150, magic84, magic30);
		btn9.setBackground(Color.white);
		btn9.addMouseListener(this);
		btnMul = new JButton();
		btnMul.setText("*");
		btnMul.setBounds(magic280, magic150, magic84, magic30);
		btnMul.setBackground(Color.white);
		btnMul.addMouseListener(this);
		btnC = new JButton();
		btnC.setText("C");
		btnC.setBounds(magic370, magic150, magic90, magic30);
		btnC.setBackground(Color.white);
		btnC.addMouseListener(this);
		btn4 = new JButton();
		btn4.setText("4");
		btn4.setBounds(magic10, magic200, magic84, magic30);
		btn4.setBackground(Color.white);
		btn4.addMouseListener(this);
		btn5 = new JButton();
		btn5.setText("5");
		btn5.setBounds(magic100, magic200, magic84, magic30);
		btn5.setBackground(Color.white);
		btn5.addMouseListener(this);
		btn6 = new JButton();
		btn6.setText("6");
		btn6.setBounds(magic190, magic200, magic84, magic30);
		btn6.setBackground(Color.white);
		btn6.addMouseListener(this);
		btnDiv = new JButton();
		btnDiv.setText("/");
		btnDiv.setBounds(magic280, magic200, magic84, magic30);
		btnDiv.setBackground(Color.white);
		btnDiv.addMouseListener(this);
		btnCr = new JButton();
		btnCr.setText(")");
		btnCr.setBounds(magic370, magic200, magic90, magic30);
		btnCr.setBackground(Color.white);
		btnCr.addMouseListener(this);
		btn1 = new JButton();
		btn1.setText("1");
		btn1.setBounds(magic10, magic250, magic84, magic30);
		btn1.setBackground(Color.white);
		btn1.addMouseListener(this);
		btn2 = new JButton();
		btn2.setText("2");
		btn2.setBounds(magic100, magic250, magic84, magic30);
		btn2.setBackground(Color.white);
		btn2.addMouseListener(this);
		btn3 = new JButton();
		btn3.setText("3");
		btn3.setBounds(magic190, magic250, magic84, magic30);
		btn3.setBackground(Color.white);
		btn3.addMouseListener(this);
		btnMi = new JButton();
		btnMi.setText("-");
		btnMi.setBounds(magic280, magic250, magic84, magic30);
		btnMi.setBackground(Color.white);
		btnMi.addMouseListener(this);
		btnDot = new JButton();
		btnCl = new JButton();
		btnCl.setText("(");
		btnCl.setBounds(magic370, magic250, magic90, magic30);
		btnCl.setBackground(Color.white);
		btnCl.addMouseListener(this);
		btnDot.setText(".");
		btnDot.setBounds(magic10, magic300, magic105, magic30);
		btnDot.setBackground(Color.white);
		btnDot.addMouseListener(this);
		btn0 = new JButton();
		btn0.setText("0");
		btn0.setBounds(magic125, magic300, magic105, magic30);
		btn0.setBackground(Color.white);
		btn0.addMouseListener(this);
		btnS = new JButton();
		btnS.setText("Save");
		btnS.setBounds(magic240, magic300, magic105, magic30);
		btnS.setBackground(Color.white);
		btnS.addMouseListener(this);
		btnP = new JButton();
		btnP.setText("+");
		btnP.setBounds(magic355, magic300, magic105, magic30);
		btnP.setBackground(Color.white);
		btnP.addMouseListener(this);
		getContentPane().setBackground(Color.black);
		getContentPane().add(expression);
		getContentPane().add(btnL);
		getContentPane().add(btnPrev);
		getContentPane().add(btnE);
		getContentPane().add(btnNext);
		getContentPane().add(btn7);
		getContentPane().add(btn8);
		getContentPane().add(btn9);
		getContentPane().add(btnMul);
		getContentPane().add(btn4);
		getContentPane().add(btn5);
		getContentPane().add(btn6);
		getContentPane().add(btnDiv);
		getContentPane().add(btn1);
		getContentPane().add(btn2);
		getContentPane().add(btn3);
		getContentPane().add(btnMi);
		getContentPane().add(btnDot);
		getContentPane().add(btn0);
		getContentPane().add(btnS);
		getContentPane().add(btnP);
		getContentPane().add(btnCur);
		getContentPane().add(btnC);
		getContentPane().add(btnCr);
		getContentPane().add(btnCl);
	}
	/**
	 *
	 * @param args we don't use it.
	 */
	public static void main(final String[] args) {
		Gui calculator = new Gui();
		calculator.setVisible(true);
	}
	@Override
	public final void mouseClicked(final MouseEvent e) {
		// TODO Auto-generated method stub
		JButton temp = (JButton) e.getSource();
		if (res) {
			res = false;
			expression.setText(null);
		}
		try {
		switch (temp.getText()) {
		case "0" :
			expression.setText(expression.getText() + "0");
			break;
		case "1" :
			expression.setText(expression.getText() + "1");
			break;
		case "2" :
			expression.setText(expression.getText() + "2");
			break;
		case "3" :
			expression.setText(expression.getText() + "3");
			break;
		case "4" :
			expression.setText(expression.getText() + "4");
			break;
		case "5" :
			expression.setText(expression.getText() + "5");
			break;
		case "6" :
			expression.setText(expression.getText() + "6");
			break;
		case "7" :
			expression.setText(expression.getText() + "7");
			break;
		case "8" :
			expression.setText(expression.getText() + "8");
			break;
		case "9" :
			expression.setText(expression.getText() + "9");
			break;
		case "." :
			expression.setText(expression.getText() + ".");
			break;
		case "+" :
			expression.setText(expression.getText() + " + ");
			break;
		case "-" :
			expression.setText(expression.getText() + " - ");
			break;
		case "*" :
			expression.setText(expression.getText() + " * ");
			break;
		case "/" :
			expression.setText(expression.getText() + " / ");
			break;
		case "(" :
			expression.setText(expression.getText() + " ( ");
			break;
		case ")" :
			expression.setText(expression.getText() + " ) ");
			break;
		case "C" :
			if (!expression.getText().equals("")) {
				expression.setText(
						expression.getText().substring(
						0, expression.getText(
							).length() - 1));
			}
			break;
		case "Save" :
			myCalc.save();
			break;
		case "Load" :
			myCalc.load();
			expression.setText(myCalc.current());
			break;
		case "Previous" :
			expression.setText(myCalc.prev());
			break;
		case "Current" :
			expression.setText(myCalc.current());
			break;
		case "Next" :
			expression.setText(myCalc.next());
			break;
		case "=" :
			res = true;
			myCalc.input(expression.getText());
			expression.setText(myCalc.getResult());
			break;
		default :
			break;
		}
		} catch (Exception r) {
			res = true;
			expression.setText("Error");
		}
	}
	@Override
	public void mouseEntered(final MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseExited(final MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mousePressed(final MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseReleased(final MouseEvent e) {
		// TODO Auto-generated method stub
	}

}
