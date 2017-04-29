package eg.edu.alexu.csd.oop.calculator.cs59;
/**
*
* @author Michael.
*
*/
public class Evaluator {
   /**
    * magic.
    */
    private final int magic10 = 10;
   /**
    * integer.
    */
    private int p = 0, d = 0;
   /**
    * stacks.
    */
    private Stack s = new Stack();
    /**
     * array of characters.
     */
    /**
     *
     * @param expression entered by user.
     * @return postfix
     */
     public final String infixToPostfix(final String expression) {
        // TODO Auto-generated method stub
        StringBuilder resu = new StringBuilder();
        if (expression.isEmpty()) {
             throw new RuntimeException();
        }
        for (int i = 0; i < expression.length(); i++) {
                 if (expression.charAt(i) == '(') {
                    p++;
                    s.push("(");
                 }  else if (expression.charAt(i) == ')') {
                    if (d == 1) {
                    throw new RuntimeException();
                    }
                    p--;
                    if (!s.isEmpty() && String.valueOf(
                    s.peek()).equals("(")) {
                    s.pop();
                    } else {
                          while (!String.valueOf(
                          s.peek()).equals("(")) {
                          resu.append(String.valueOf(
                        s.pop()));
                          resu.append(' ');
                          }
                          s.pop();
                          }
                    } else if (expression.charAt(i) == '/') {
                    if (d == 1) {
                    throw new RuntimeException();
                    }
                    d = 1;
                    if (!s.isEmpty() && (String.valueOf(
                    s.peek()).equals("/")
                    || String.valueOf(
                    s.peek()).equals("*"))) {
                    resu.append(
                    String.valueOf(s.pop()));
                    resu.append(" ");
                    s.push("/");
                    } else {
                      s.push("/");
                 }
                } else if (expression.charAt(i) == '*') {
                    if (d == 1) {
                         throw new RuntimeException();
                    }
                    d = 1;
                    if (!s.isEmpty() && (
                         String.valueOf(
                         s.peek()).equals("/")
                         || String.valueOf(
                         s.peek()).equals("*"))) {
                         resu.append(
                         String.valueOf(s.pop()));
                         s.push("*");
                         resu.append(" ");
                     } else {
                         s.push("*");
                     }
                    } else if (
                        expression.charAt(i) == '+') {
                        if (d == 1) {
                           throw new RuntimeException();
                        }
                        d = 1;
                        while (!s.isEmpty() && (
                         !String.valueOf(
                         s.peek()).equals("(")
                         )) {
                             resu.append(
                           String.valueOf(s.pop()));
                           resu.append(" ");
                            }
                            s.push("+");
                    } else if (
                        expression.charAt(i) == '-') {
                        if (d == 1) {
                          throw new RuntimeException();
                        }
                        d = 1;
                     while (!s.isEmpty() && (
                     !String.valueOf(
                     s.peek()).equals("(")
                     )) {
                       resu.append(
                       String.valueOf(s.pop()));
                       resu.append(" ");
                       }
                       s.push("-");
        } else if (expression.charAt(i) == '.') {
             resu.append(String.valueOf(
                   expression.charAt(i)));
          d = 0;
        } else if (expression.charAt(i) != ' ') {
              resu.append(String.valueOf(
                    expression.charAt(i)));
               if (i + 1 < expression.length()
                       && String.valueOf(
                 expression.charAt(i + 1)).equals(".")) {
                    d = 0;
               } else if (i + 1 < expression.length()
	 		 	&& !String.valueOf(
	 		 	expression.charAt(i + 1)).equals(" ")
	 			&& !String.valueOf(
	 		 	expression.charAt(i + 1)).equals("/")
	 			&& !String.valueOf(
	 		 	expression.charAt(i + 1)).equals("*")
	 			&& !String.valueOf(
	 		 	expression.charAt(i + 1)).equals("-")
	 			&& !String.valueOf(
	 		  	expression.charAt(i + 1)).equals("+")
	 		 	&& !String.valueOf(
	 		 	expression.charAt(i + 1)).equals(")")
	 		 	&& !String.valueOf(
	 		 	expression.charAt(i + 1)).equals("(")) {
	 					d = 0;
	 				} else {
	 					resu.append(" ");
	 					d = 0;
	 				}
	 				}
		}
		if (d == 1 || p != 0) {
			throw new RuntimeException();
		}
		while (s.size() != 0) {
			resu.append(String.valueOf(s.pop()));
			if (s.size() != 0) {
				resu.append(" ");
			}
		}
		return resu.toString();
	}
	/**
	 *
	 * @param expression to be evaluated.
	 * @return answer of expression
	 */
	public final Double evaluate(final String expression) {
		// TODO Auto-generated method stub
		if (expression.isEmpty()) {
			throw new RuntimeException();
		}
		Object v = null, f = null;
		double k;
		String[] exp = expression.split(" ");
		for (int i = 0; i < exp.length; i++) {
			if (exp[i].equals("/")) {
				if (s.size() >= 2) {
					f = s.pop();
					v = s.pop();
				} else {
					throw new RuntimeException();
				}
				if ((Double.valueOf(
					String.valueOf(f))) == (float) 0) {
						throw new RuntimeException();
						 }
				k = (Double.valueOf(
						String.valueOf(v))
						/ (Double.valueOf(
						 String.valueOf(f))));
				s.push(k);
			} else if (exp[i].equals("*")) {
				if (s.size() >= 2) {
					f = s.pop();
					v = s.pop();
				} else {
					throw new RuntimeException();
				}
				k = (Double.valueOf(
						String.valueOf(v))
						* (Double.valueOf(
						 String.valueOf(f))));
				s.push(k);
			} else if (exp[i].equals("+")) {
				if (s.size() >= 2) {
					f = s.pop();
					v = s.pop();
				} else {
					throw new RuntimeException();
				}
				k = (Double.valueOf(
						String.valueOf(v))
						+ (Double.valueOf(
						 String.valueOf(f))));
				s.push(k);
			} else if (exp[i].equals("-")) {
				if (s.size() >= 2) {
					f = s.pop();
					v = s.pop();
				} else {
					throw new RuntimeException();
				}
				k = (Double.valueOf(
						String.valueOf(v))
						- (Double.valueOf(
						 String.valueOf(f))));
				s.push(k);
			} else {
				s.push(exp[i]);
			}
		}
        if (s.size() != 1) {
        	throw new RuntimeException();
        }
        Double h = Double.parseDouble(String.valueOf(s.pop()));
		return h;
	}
}
