// sci_calculator.js

const readline = require("readline");

// Create input interface
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

// Display menu
console.log(`
========= Scientific Calculator =========
Available operations:
1. Addition (+)
2. Subtraction (-)
3. Multiplication (*)
4. Division (/)
5. Power (pow)
6. Square Root (sqrt)
7. Sine (sin)
8. Cosine (cos)
9. Tangent (tan)
10. Logarithm (log)
11. Exponential (exp)
12. Absolute (abs)
========================================
`);

rl.question("Enter operation name (e.g., +, sin, pow): ", (op) => {
  if (["+", "-", "*", "/", "pow"].includes(op)) {
    rl.question("Enter first number: ", (a) => {
      rl.question("Enter second number: ", (b) => {
        const x = parseFloat(a);
        const y = parseFloat(b);
        let result;
        switch (op) {
          case "+": result = x + y; break;
          case "-": result = x - y; break;
          case "*": result = x * y; break;
          case "/": result = y !== 0 ? x / y : "Division by zero error"; break;
          case "pow": result = Math.pow(x, y); break;
        }
        console.log(`Result: ${result}`);
        rl.close();
      });
    });
  } else {
    rl.question("Enter value: ", (val) => {
      const x = parseFloat(val);
      let result;
      switch (op) {
        case "sqrt": result = Math.sqrt(x); break;
        case "sin": result = Math.sin(x); break;
        case "cos": result = Math.cos(x); break;
        case "tan": result = Math.tan(x); break;
        case "log": result = Math.log(x); break;
        case "exp": result = Math.exp(x); break;
        case "abs": result = Math.abs(x); break;
        default: result = "Invalid operation.";
      }
      console.log(`Result: ${result}`);
      rl.close();
    });
  }
});
