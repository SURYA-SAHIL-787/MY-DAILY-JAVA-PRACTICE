# Parsing, Casting, Type Promotion, and Wrapper Classes in Java

## Complete Lecture Notes for Solving Problems on Your Own

---

# 1. Why These Topics Matter

Java is a **strongly typed language**.

This means every variable has a fixed data type.

```java
int age = 20;
double percentage = 87.5;
char grade = 'A';
String number = "123";
```

Sometimes, a value is available in one data type, but the program needs it in another data type.

Examples:

- A number comes from the user as a `String`, but you need it as an `int`.
- An `int` must be stored in a `double`.
- A `double` must be converted into an `int`.
- A primitive value must be stored inside a wrapper object.
- Two different data types are used in the same arithmetic expression.

To handle these situations, Java provides:

1. **Parsing**
2. **Type Casting**
3. **Type Promotion**
4. **Wrapper Classes**
5. **Autoboxing**
6. **Unboxing**

---

# 2. Primitive Data Types You Must Know

Java has eight primitive data types.

| Data Type | Used For | Example |
|---|---|---|
| `byte` | Very small integers | `byte a = 10;` |
| `short` | Small integers | `short b = 2000;` |
| `int` | Normal integers | `int c = 50000;` |
| `long` | Very large integers | `long d = 9000000000L;` |
| `float` | Decimal values with lower precision | `float e = 12.5f;` |
| `double` | Decimal values with higher precision | `double f = 45.67;` |
| `char` | One character | `char g = 'A';` |
| `boolean` | `true` or `false` | `boolean h = true;` |

## Integer Type Order

From smaller to larger:

```text
byte → short → int → long
```

## Decimal Type Order

```text
float → double
```

A larger data type can generally store a wider range of values.

---

# 3. Parsing

## 3.1 What Is Parsing?

**Parsing means converting a String into another data type.**

Example:

```java
String number = "123";
int value = Integer.parseInt(number);
```

Here:

```text
"123"     → String
123       → int
```

Parsing is mainly used when numeric or boolean information is stored as text.

---

## 3.2 Why Is Parsing Needed?

Input taken using methods such as:

```java
Scanner.nextLine()
```

is usually received as a `String`.

Data read from:

- Forms
- Text files
- Web applications
- Command-line arguments
- Databases
- APIs

may also arrive as text.

To perform arithmetic, the text must first be converted into a numeric data type.

---

## 3.3 Important Parsing Methods

| String Must Become | Method |
|---|---|
| `byte` | `Byte.parseByte()` |
| `short` | `Short.parseShort()` |
| `int` | `Integer.parseInt()` |
| `long` | `Long.parseLong()` |
| `float` | `Float.parseFloat()` |
| `double` | `Double.parseDouble()` |
| `boolean` | `Boolean.parseBoolean()` |

There is no commonly used `Character.parseChar()` method.

To get a character from a String, use:

```java
char ch = text.charAt(0);
```

---

# 4. Parsing String into int

```java
public class Main {
    public static void main(String[] args) {
        String number = "123";

        int value = Integer.parseInt(number);

        System.out.println(value);
    }
}
```

Output:

```text
123
```

## Important Point

```java
String number = "123";
```

contains text.

After parsing:

```java
int value = 123;
```

contains an actual integer.

Now arithmetic is possible.

```java
System.out.println(value + 10);
```

Output:

```text
133
```

Without parsing:

```java
System.out.println(number + 10);
```

Output:

```text
12310
```

This happens because String concatenation is performed.

---

# 5. Parsing String into long

```java
public class Main {
    public static void main(String[] args) {
        String largeNumber = "9876543210";

        long value = Long.parseLong(largeNumber);

        System.out.println(value);
    }
}
```

Output:

```text
9876543210
```

Use `long` when the value may be larger than the range of `int`.

---

# 6. Parsing String into double

```java
public class Main {
    public static void main(String[] args) {
        String decimalNumber = "45.67";

        double value = Double.parseDouble(decimalNumber);

        System.out.println(value);
    }
}
```

Output:

```text
45.67
```

---

# 7. Parsing String into boolean

```java
public class Main {
    public static void main(String[] args) {
        String text = "true";

        boolean value = Boolean.parseBoolean(text);

        System.out.println(value);
    }
}
```

Output:

```text
true
```

## Important Behavior

`Boolean.parseBoolean()` returns `true` only when the String is equal to `"true"`, ignoring uppercase and lowercase.

```java
Boolean.parseBoolean("true");   // true
Boolean.parseBoolean("TRUE");   // true
Boolean.parseBoolean("TrUe");   // true
Boolean.parseBoolean("false");  // false
Boolean.parseBoolean("yes");    // false
Boolean.parseBoolean("1");      // false
```

It does not throw an error for `"yes"` or `"1"`. It simply returns `false`.

---

# 8. Invalid Parsing and NumberFormatException

Parsing works only when the String contains a valid value.

Valid:

```java
Integer.parseInt("123");
Double.parseDouble("45.67");
```

Invalid:

```java
Integer.parseInt("hello");
Integer.parseInt("12.5");
Integer.parseInt("123abc");
```

These invalid conversions cause:

```text
NumberFormatException
```

Example:

```java
public class Main {
    public static void main(String[] args) {
        String text = "hello";

        int value = Integer.parseInt(text);

        System.out.println(value);
    }
}
```

The program crashes because `"hello"` cannot be converted into an integer.

---

# 9. Converting Primitive Values into String

This is the reverse of parsing.

```text
Primitive value → String
```

Common methods:

```java
String.valueOf(value)
```

or

```java
WrapperClass.toString(value)
```

---

# 10. Converting int into String

## Method 1: String.valueOf()

```java
public class Main {
    public static void main(String[] args) {
        int number = 123;

        String text = String.valueOf(number);

        System.out.println(text);
    }
}
```

## Method 2: Integer.toString()

```java
String text = Integer.toString(number);
```

## Method 3: Concatenation

```java
String text = number + "";
```

This works, but `String.valueOf()` is clearer and better for learning.

---

# 11. Converting double into String

```java
public class Main {
    public static void main(String[] args) {
        double number = 45.67;

        String text = String.valueOf(number);

        System.out.println(text);
    }
}
```

Another method:

```java
String text = Double.toString(number);
```

---

# 12. Parsing vs Casting

This difference is extremely important.

## Parsing

Parsing is used mainly when converting a `String` into a primitive value.

```java
String text = "100";
int number = Integer.parseInt(text);
```

## Casting

Casting is used when converting one compatible data type into another data type.

```java
double value = 45.67;
int number = (int) value;
```

## Comparison

| Parsing | Casting |
|---|---|
| Converts textual data into another type | Converts one compatible data type into another |
| Usually involves `String` | Usually involves primitive numeric types |
| Uses methods like `parseInt()` | Uses automatic conversion or `(type)` syntax |
| Example: `"123"` to `123` | Example: `45.67` to `45` |

This is invalid:

```java
int number = (int) "123";
```

A String cannot be converted into an int using casting.

Correct:

```java
int number = Integer.parseInt("123");
```

---

# 13. Type Casting

## 13.1 What Is Type Casting?

**Type casting means converting a value from one data type into another compatible data type.**

There are two main types:

1. Implicit casting
2. Explicit casting

---

# 14. Implicit Casting

Implicit casting is also called:

- Widening casting
- Automatic casting
- Widening conversion

It happens automatically when a smaller data type is converted into a larger compatible data type.

```java
int number = 100;
long value = number;
```

No casting operator is required.

---

## 14.1 Common Widening Order

```text
byte → short → int → long → float → double
```

Also:

```text
char → int → long → float → double
```

Examples:

```java
byte a = 10;
short b = a;

short c = 100;
int d = c;

int e = 500;
long f = e;

long g = 1000L;
float h = g;

float i = 12.5f;
double j = i;
```

---

## 14.2 Why Is Widening Automatic?

A larger type can usually store all values of a smaller type.

Example:

```java
int number = 25;
double result = number;
```

Output:

```text
25.0
```

The integer is safely represented as a double.

---

# 15. int to long

```java
public class Main {
    public static void main(String[] args) {
        int intValue = 500;

        long longValue = intValue;

        System.out.println("int value: " + intValue);
        System.out.println("long value: " + longValue);
    }
}
```

No explicit cast is required.

---

# 16. int to double

```java
public class Main {
    public static void main(String[] args) {
        int intValue = 25;

        double doubleValue = intValue;

        System.out.println("int value: " + intValue);
        System.out.println("double value: " + doubleValue);
    }
}
```

Output:

```text
int value: 25
double value: 25.0
```

---

# 17. float to double

```java
public class Main {
    public static void main(String[] args) {
        float floatValue = 12.5f;

        double doubleValue = floatValue;

        System.out.println("float value: " + floatValue);
        System.out.println("double value: " + doubleValue);
    }
}
```

This is widening conversion.

---

# 18. Explicit Casting

Explicit casting is also called:

- Narrowing casting
- Manual casting
- Narrowing conversion

It is required when converting a larger data type into a smaller data type.

Syntax:

```java
smallerType variable = (smallerType) largerValue;
```

Example:

```java
double value = 45.67;
int number = (int) value;
```

---

## 18.1 Why Is Explicit Casting Required?

A smaller type may not be able to store the complete value.

Possible problems:

- Decimal part may be lost.
- Value may exceed the target type's range.
- Overflow may occur.
- Final result may be unexpected.

Java requires the programmer to explicitly accept this risk.

---

# 19. double to int

```java
public class Main {
    public static void main(String[] args) {
        double doubleValue = 45.67;

        int intValue = (int) doubleValue;

        System.out.println("double value: " + doubleValue);
        System.out.println("int value: " + intValue);
    }
}
```

Output:

```text
double value: 45.67
int value: 45
```

## Important Rule

Casting from `double` to `int` removes the decimal part.

It does not round the value.

```java
(int) 45.99   // 45
(int) 45.10   // 45
(int) -45.99  // -45
```

The fractional part is truncated toward zero.

For rounding, use:

```java
Math.round()
```

Example:

```java
long rounded = Math.round(45.67);
```

---

# 20. long to int

```java
public class Main {
    public static void main(String[] args) {
        long longValue = 5000L;

        int intValue = (int) longValue;

        System.out.println("long value: " + longValue);
        System.out.println("int value: " + intValue);
    }
}
```

This works safely because `5000` fits inside the `int` range.

However, a very large `long` may produce an unexpected result.

```java
long longValue = 3000000000L;
int intValue = (int) longValue;

System.out.println(intValue);
```

The result is not `3000000000` because that value is outside the `int` range.

This is called overflow or wraparound behavior.

---

# 21. int to char

A `char` stores a Unicode code unit.

You can convert an integer into a character using explicit casting.

```java
public class Main {
    public static void main(String[] args) {
        int value = 65;

        char character = (char) value;

        System.out.println(character);
    }
}
```

Output:

```text
A
```

Because Unicode value `65` represents `A`.

---

# 22. char to int

A character can be converted into an integer to obtain its Unicode value.

```java
public class Main {
    public static void main(String[] args) {
        char character = 'A';

        int value = character;

        System.out.println(value);
    }
}
```

Output:

```text
65
```

Explicit casting also works:

```java
int value = (int) character;
```

But it is not required because `char` to `int` is a widening conversion.

---

# 23. ASCII and Unicode

Many beginner questions say:

> Convert a character into an integer and display its ASCII value.

Java actually stores characters using Unicode.

For common English letters, digits, and symbols, Unicode values match ASCII values.

Examples:

| Character | Value |
|---|---:|
| `'A'` | 65 |
| `'B'` | 66 |
| `'Z'` | 90 |
| `'a'` | 97 |
| `'b'` | 98 |
| `'0'` | 48 |
| `'1'` | 49 |
| `' '` | 32 |

Example:

```java
char ch = 'a';
int value = ch;

System.out.println(value);
```

Output:

```text
97
```

---

# 24. Storing Unicode Value 65 in char

```java
public class Main {
    public static void main(String[] args) {
        char character = 65;

        System.out.println(character);
    }
}
```

Output:

```text
A
```

This is allowed when the integer value is a constant within the valid `char` range.

With a variable, explicit casting is normally required:

```java
int value = 65;
char character = (char) value;
```

---

# 25. Constant Assignment vs Variable Casting

This may look confusing:

```java
char ch = 65;
```

works.

But:

```java
int value = 65;
char ch = value;
```

does not compile.

Why?

`65` is a compile-time constant, and Java can verify that it fits inside `char`.

But `value` is an `int` variable. Java does not automatically assume that its value will always fit.

Correct:

```java
int value = 65;
char ch = (char) value;
```

The same idea applies to `byte` and `short`.

```java
byte a = 100;    // allowed
int number = 100;
byte b = (byte) number;
```

---

# 26. Type Promotion

## 26.1 What Is Type Promotion?

**Type promotion happens when Java automatically converts operands into a larger or standard type while evaluating an expression.**

Example:

```java
byte a = 10;
byte b = 20;

int result = a + b;
```

Although both values are `byte`, the result of `a + b` is an `int`.

---

# 27. Important Type Promotion Rules

## Rule 1: byte, short, and char become int in arithmetic

When `byte`, `short`, or `char` values are used in arithmetic expressions, Java promotes them to `int`.

```java
byte + byte   → int
short + short → int
char + char   → int
byte + short  → int
char + int    → int
```

## Rule 2: If one operand is long, the result becomes long

```java
int + long → long
```

## Rule 3: If one operand is float, the result becomes float

```java
long + float → float
```

## Rule 4: If one operand is double, the result becomes double

```java
int + double → double
float + double → double
```

## Promotion Hierarchy

```text
double
  ↑
float
  ↑
long
  ↑
int
```

For arithmetic, `byte`, `short`, and `char` first become `int`.

---

# 28. Adding Two byte Values

```java
public class Main {
    public static void main(String[] args) {
        byte first = 10;
        byte second = 20;

        int result = first + second;

        System.out.println(result);
    }
}
```

Output:

```text
30
```

This does not compile:

```java
byte result = first + second;
```

because `first + second` produces an `int`.

You can explicitly cast:

```java
byte result = (byte) (first + second);
```

But this may cause overflow if the result is outside the range of `byte`.

Best practice:

```java
int result = first + second;
```

---

# 29. Why byte + byte Produces int

Java performs integer arithmetic mainly using `int` or larger types.

This provides:

- More consistent arithmetic
- Fewer immediate overflows
- Simpler CPU-level processing
- Common rules for `byte`, `short`, and `char`

Therefore:

```java
byte a = 10;
byte b = 20;
```

becomes conceptually:

```java
int result = (int) a + (int) b;
```

---

# 30. Adding char and int

```java
public class Main {
    public static void main(String[] args) {
        char character = 'A';
        int number = 5;

        int result = character + number;

        System.out.println(result);
    }
}
```

Output:

```text
70
```

Explanation:

```text
'A' = 65
65 + 5 = 70
```

To display the resulting character:

```java
char result = (char) (character + number);

System.out.println(result);
```

Output:

```text
F
```

---

# 31. Adding int and double

```java
public class Main {
    public static void main(String[] args) {
        int number = 10;
        double decimal = 5.5;

        double result = number + decimal;

        System.out.println(result);
    }
}
```

Output:

```text
15.5
```

The `int` is promoted into a `double`.

Conceptually:

```java
double result = (double) number + decimal;
```

---

# 32. Expression with Different Data Types

```java
public class Main {
    public static void main(String[] args) {
        byte a = 10;
        int b = 20;
        long c = 30L;
        float d = 2.5f;
        double e = 5.75;

        double result = a + b + c + d + e;

        System.out.println(result);
    }
}
```

Final result type:

```text
double
```

Why?

The expression contains a `double`, so all smaller numeric values are promoted as needed, and the final result becomes `double`.

---

# 33. Promotion Happens Step by Step

Consider:

```java
int a = 10;
long b = 20L;
float c = 2.5f;
double d = 4.75;

double result = a + b + c + d;
```

Evaluation:

```text
a + b
int + long
result: long
```

Then:

```text
long + float
result: float
```

Then:

```text
float + double
result: double
```

Final result:

```text
double
```

---

# 34. Integer Division Before Promotion

This is one of the most important interview and exam concepts.

```java
int a = 5;
int b = 2;

double result = a / b;

System.out.println(result);
```

Output:

```text
2.0
```

Many beginners expect `2.5`.

Why does Java produce `2.0`?

Because:

```java
a / b
```

is evaluated first.

Both operands are `int`, so integer division happens:

```text
5 / 2 = 2
```

Then `2` is converted into `double`:

```text
2 → 2.0
```

## Correct Method

Cast one operand before division:

```java
double result = (double) a / b;
```

Now:

```text
(double) 5 / 2
5.0 / 2
2.5
```

Another method:

```java
double result = a / 2.0;
```

---

# 35. Compound Assignment and Hidden Casting

Consider:

```java
byte value = 10;
value += 5;
```

This works.

But:

```java
value = value + 5;
```

does not compile without casting.

Why?

`value + 5` produces an `int`.

This:

```java
value += 5;
```

behaves approximately like:

```java
value = (byte) (value + 5);
```

Compound assignment operators perform an implicit narrowing conversion.

Examples:

```java
+=
-=
*=
/=
%=
```

Be careful because overflow can still happen.

---

# 36. Wrapper Classes

## 36.1 What Is a Wrapper Class?

A wrapper class is an object-based version of a primitive data type.

Each primitive data type has a corresponding wrapper class.

| Primitive | Wrapper Class |
|---|---|
| `byte` | `Byte` |
| `short` | `Short` |
| `int` | `Integer` |
| `long` | `Long` |
| `float` | `Float` |
| `double` | `Double` |
| `char` | `Character` |
| `boolean` | `Boolean` |

Notice:

- `int` uses `Integer`, not `Int`.
- `char` uses `Character`, not `Char`.

---

# 37. Why Wrapper Classes Are Needed

Primitive values are not objects.

Some Java features work only with objects.

Examples:

- Collections such as `ArrayList`
- Generics
- Utility methods
- Nullable values
- Object-based APIs

This is invalid:

```java
ArrayList<int> numbers;
```

This is correct:

```java
ArrayList<Integer> numbers;
```

Wrapper classes allow primitive-like values to be used where objects are required.

---

# 38. Primitive vs Wrapper Class

| Primitive | Wrapper |
|---|---|
| Stores a simple value | Stores the value inside an object |
| Usually faster | Has object-related overhead |
| Cannot be `null` | Can be `null` |
| Used for normal calculations | Used in collections and object-based APIs |
| Example: `int x = 10;` | Example: `Integer x = 10;` |

---

# 39. Creating Wrapper Objects

Modern Java usually uses autoboxing.

```java
Integer value = 100;
```

Older style:

```java
Integer value = Integer.valueOf(100);
```

Avoid using deprecated constructor style:

```java
Integer value = new Integer(100);
```

Use:

```java
Integer.valueOf(100);
```

or simply:

```java
Integer value = 100;
```

---

# 40. Integer Wrapper Object

```java
public class Main {
    public static void main(String[] args) {
        int primitiveValue = 100;

        Integer wrapperValue = Integer.valueOf(primitiveValue);

        System.out.println(wrapperValue);
    }
}
```

Simpler modern version:

```java
Integer wrapperValue = 100;
```

---

# 41. Long Wrapper Object

```java
public class Main {
    public static void main(String[] args) {
        Long value = 9876543210L;

        System.out.println(value);
    }
}
```

The `L` suffix tells Java that the integer literal is a `long`.

---

# 42. Double Wrapper Object

```java
public class Main {
    public static void main(String[] args) {
        Double value = 45.67;

        System.out.println(value);
    }
}
```

Decimal literals are `double` by default.

---

# 43. Float Wrapper Object

```java
public class Main {
    public static void main(String[] args) {
        Float value = 12.5f;

        System.out.println(value);
    }
}
```

The `f` suffix is required because decimal literals are `double` by default.

This is invalid:

```java
Float value = 12.5;
```

Correct:

```java
Float value = 12.5f;
```

---

# 44. Character Wrapper Object

```java
public class Main {
    public static void main(String[] args) {
        Character value = 'A';

        System.out.println(value);
    }
}
```

A character uses single quotes.

```java
'A'
```

A String uses double quotes.

```java
"A"
```

---

# 45. Boolean Wrapper Object

```java
public class Main {
    public static void main(String[] args) {
        Boolean value = true;

        System.out.println(value);
    }
}
```

Do not use quotes for a primitive boolean:

```java
boolean value = true;
```

This is a String:

```java
String value = "true";
```

---

# 46. Byte Wrapper Object

```java
public class Main {
    public static void main(String[] args) {
        Byte value = 100;

        System.out.println(value);
    }
}
```

The value must fit inside the `byte` range.

---

# 47. Short Wrapper Object

```java
public class Main {
    public static void main(String[] args) {
        Short value = 30000;

        System.out.println(value);
    }
}
```

The value must fit inside the `short` range.

---

# 48. Autoboxing

## 48.1 What Is Autoboxing?

**Autoboxing is the automatic conversion of a primitive value into its corresponding wrapper object.**

Example:

```java
int number = 100;
Integer object = number;
```

Java automatically converts:

```text
int → Integer
```

Conceptually:

```java
Integer object = Integer.valueOf(number);
```

---

# 49. Autoboxing Example

```java
public class Main {
    public static void main(String[] args) {
        int primitiveValue = 100;

        Integer wrapperObject = primitiveValue;

        System.out.println("Primitive: " + primitiveValue);
        System.out.println("Wrapper: " + wrapperObject);
    }
}
```

---

# 50. Unboxing

## 50.1 What Is Unboxing?

**Unboxing is the automatic conversion of a wrapper object into its corresponding primitive value.**

Example:

```java
Integer object = 100;
int number = object;
```

Java automatically converts:

```text
Integer → int
```

Conceptually:

```java
int number = object.intValue();
```

---

# 51. Unboxing Example

```java
public class Main {
    public static void main(String[] args) {
        Integer wrapperObject = 200;

        int primitiveValue = wrapperObject;

        System.out.println("Wrapper: " + wrapperObject);
        System.out.println("Primitive: " + primitiveValue);
    }
}
```

---

# 52. Wrapper Value Extraction Methods

Wrapper classes provide methods to obtain primitive values.

```java
Integer value = 100;

byte byteValue = value.byteValue();
short shortValue = value.shortValue();
int intValue = value.intValue();
long longValue = value.longValue();
float floatValue = value.floatValue();
double doubleValue = value.doubleValue();
```

Example:

```java
Double value = 45.67;

int number = value.intValue();

System.out.println(number);
```

Output:

```text
45
```

---

# 53. Wrapper Parsing vs valueOf()

This distinction is important.

## parseInt()

```java
int number = Integer.parseInt("123");
```

Returns a primitive:

```text
int
```

## valueOf()

```java
Integer number = Integer.valueOf("123");
```

Returns a wrapper object:

```text
Integer
```

Comparison:

| Method | Return Type |
|---|---|
| `Integer.parseInt("123")` | `int` |
| `Integer.valueOf("123")` | `Integer` |
| `Double.parseDouble("45.6")` | `double` |
| `Double.valueOf("45.6")` | `Double` |

Because of autoboxing and unboxing, both may sometimes appear to work similarly, but their return types are different.

---

# 54. Null and Unboxing Danger

Wrapper objects can contain `null`.

```java
Integer value = null;
```

Primitive types cannot contain `null`.

This causes an error:

```java
Integer object = null;
int number = object;
```

During unboxing, Java tries to extract an `int` from `null`.

This causes:

```text
NullPointerException
```

Safe approach:

```java
Integer object = null;

if (object != null) {
    int number = object;
    System.out.println(number);
}
```

---

# 55. Comparing Wrapper Objects

Do not always use `==` to compare wrapper values.

Example:

```java
Integer a = 1000;
Integer b = 1000;

System.out.println(a == b);
```

The result may be `false` because `==` compares object references.

Use:

```java
System.out.println(a.equals(b));
```

Output:

```text
true
```

## Rule

For wrapper objects:

```java
a.equals(b)
```

is usually the correct way to compare values.

For primitives:

```java
a == b
```

is correct.

---

# 56. Common Mistakes

## Mistake 1: Casting a String

Wrong:

```java
int number = (int) "123";
```

Correct:

```java
int number = Integer.parseInt("123");
```

---

## Mistake 2: Forgetting Explicit Cast

Wrong:

```java
double value = 45.67;
int number = value;
```

Correct:

```java
int number = (int) value;
```

---

## Mistake 3: Expecting Rounding

```java
int number = (int) 45.99;
```

Result:

```text
45
```

Casting truncates. It does not round.

---

## Mistake 4: Storing byte Arithmetic in byte

Wrong:

```java
byte a = 10;
byte b = 20;
byte result = a + b;
```

Correct:

```java
int result = a + b;
```

---

## Mistake 5: Forgetting f for float

Wrong:

```java
float value = 12.5;
```

Correct:

```java
float value = 12.5f;
```

---

## Mistake 6: Forgetting L for Large long Literal

Wrong:

```java
long value = 9876543210;
```

Correct:

```java
long value = 9876543210L;
```

---

## Mistake 7: Confusing char and String

Character:

```java
char ch = 'A';
```

String:

```java
String text = "A";
```

---

## Mistake 8: Using Integer Division Accidentally

```java
double result = 5 / 2;
```

Result:

```text
2.0
```

Correct:

```java
double result = 5.0 / 2;
```

or:

```java
double result = (double) 5 / 2;
```

---

# 57. How to Decide Which Conversion to Use

Use this decision process.

## Situation 1: Is the original value a String?

Example:

```java
String value = "123";
```

Use parsing:

```java
int number = Integer.parseInt(value);
```

---

## Situation 2: Do you need to convert a primitive into String?

Use:

```java
String text = String.valueOf(value);
```

---

## Situation 3: Are both values numeric primitive types?

Use casting.

Smaller to larger:

```java
long value = intValue;
```

This is implicit casting.

Larger to smaller:

```java
int value = (int) doubleValue;
```

This is explicit casting.

---

## Situation 4: Is arithmetic being performed with different types?

Think about type promotion.

Ask:

1. Are `byte`, `short`, or `char` involved?
2. Is there a `long`?
3. Is there a `float`?
4. Is there a `double`?

The largest promoted type generally decides the result type.

---

## Situation 5: Does the value need to be an object?

Use the wrapper class.

```java
Integer value = 100;
```

---

## Situation 6: Is a primitive being assigned to a wrapper?

That is autoboxing.

```java
Integer value = 100;
```

---

## Situation 7: Is a wrapper being assigned to a primitive?

That is unboxing.

```java
Integer object = 100;
int value = object;
```

---

# 58. Problem-Solving Templates

## Template 1: String to int

```java
String text = "123";
int value = Integer.parseInt(text);

System.out.println(value);
```

## Template 2: String to long

```java
String text = "9876543210";
long value = Long.parseLong(text);

System.out.println(value);
```

## Template 3: String to double

```java
String text = "45.67";
double value = Double.parseDouble(text);

System.out.println(value);
```

## Template 4: String to boolean

```java
String text = "true";
boolean value = Boolean.parseBoolean(text);

System.out.println(value);
```

## Template 5: Primitive to String

```java
int value = 123;
String text = String.valueOf(value);

System.out.println(text);
```

## Template 6: Widening Casting

```java
int smallValue = 100;
double largeValue = smallValue;

System.out.println(smallValue);
System.out.println(largeValue);
```

## Template 7: Narrowing Casting

```java
double largeValue = 45.67;
int smallValue = (int) largeValue;

System.out.println(largeValue);
System.out.println(smallValue);
```

## Template 8: Type Promotion

```java
int a = 10;
double b = 5.5;

double result = a + b;

System.out.println(result);
```

## Template 9: Autoboxing

```java
int primitive = 100;
Integer wrapper = primitive;

System.out.println(wrapper);
```

## Template 10: Unboxing

```java
Integer wrapper = 100;
int primitive = wrapper;

System.out.println(primitive);
```

---

# 59. Solutions to the Easy-Level Problems

## 1. String "123" to int

```java
public class Main {
    public static void main(String[] args) {
        String text = "123";
        int number = Integer.parseInt(text);

        System.out.println(number);
    }
}
```

## 2. Large String number to long

```java
public class Main {
    public static void main(String[] args) {
        String text = "9876543210";
        long number = Long.parseLong(text);

        System.out.println(number);
    }
}
```

## 3. String "45.67" to double

```java
public class Main {
    public static void main(String[] args) {
        String text = "45.67";
        double number = Double.parseDouble(text);

        System.out.println(number);
    }
}
```

## 4. String "true" to boolean

```java
public class Main {
    public static void main(String[] args) {
        String text = "true";
        boolean value = Boolean.parseBoolean(text);

        System.out.println(value);
    }
}
```

## 5. int to String

```java
public class Main {
    public static void main(String[] args) {
        int number = 123;
        String text = String.valueOf(number);

        System.out.println(text);
    }
}
```

## 6. double to String

```java
public class Main {
    public static void main(String[] args) {
        double number = 45.67;
        String text = String.valueOf(number);

        System.out.println(text);
    }
}
```

## 7. char to integer Unicode/ASCII value

```java
public class Main {
    public static void main(String[] args) {
        char character = 'A';
        int value = character;

        System.out.println(value);
    }
}
```

## 8. Unicode value 65 to char

```java
public class Main {
    public static void main(String[] args) {
        int value = 65;
        char character = (char) value;

        System.out.println(character);
    }
}
```

---

# 60. Solutions to the Medium-Level Problems

## 1. int to long

```java
public class Main {
    public static void main(String[] args) {
        int intValue = 100;
        long longValue = intValue;

        System.out.println("int: " + intValue);
        System.out.println("long: " + longValue);
    }
}
```

## 2. int to double

```java
public class Main {
    public static void main(String[] args) {
        int intValue = 25;
        double doubleValue = intValue;

        System.out.println("int: " + intValue);
        System.out.println("double: " + doubleValue);
    }
}
```

## 3. float to double

```java
public class Main {
    public static void main(String[] args) {
        float floatValue = 12.5f;
        double doubleValue = floatValue;

        System.out.println("float: " + floatValue);
        System.out.println("double: " + doubleValue);
    }
}
```

## 4. double to int

```java
public class Main {
    public static void main(String[] args) {
        double doubleValue = 45.67;
        int intValue = (int) doubleValue;

        System.out.println("double: " + doubleValue);
        System.out.println("int: " + intValue);
    }
}
```

## 5. long to int

```java
public class Main {
    public static void main(String[] args) {
        long longValue = 5000L;
        int intValue = (int) longValue;

        System.out.println("long: " + longValue);
        System.out.println("int: " + intValue);
    }
}
```

## 6. int to char

```java
public class Main {
    public static void main(String[] args) {
        int value = 66;
        char character = (char) value;

        System.out.println(character);
    }
}
```

## 7. Implicit Casting

```java
public class Main {
    public static void main(String[] args) {
        int smallValue = 100;
        double largeValue = smallValue;

        System.out.println(smallValue);
        System.out.println(largeValue);
    }
}
```

## 8. Explicit Casting

```java
public class Main {
    public static void main(String[] args) {
        double largeValue = 99.99;
        int smallValue = (int) largeValue;

        System.out.println(largeValue);
        System.out.println(smallValue);
    }
}
```

## 9. Add Two byte Values

```java
public class Main {
    public static void main(String[] args) {
        byte first = 10;
        byte second = 20;

        int result = first + second;

        System.out.println(result);
    }
}
```

## 10. Add char and int

```java
public class Main {
    public static void main(String[] args) {
        char character = 'A';
        int number = 5;

        int result = character + number;

        System.out.println(result);
    }
}
```

## 11. Add int and double

```java
public class Main {
    public static void main(String[] args) {
        int number = 10;
        double decimal = 5.5;

        double result = number + decimal;

        System.out.println(result);
    }
}
```

## 12. Arithmetic Expression with Different Types

```java
public class Main {
    public static void main(String[] args) {
        byte a = 10;
        int b = 20;
        long c = 30L;
        float d = 2.5f;
        double e = 5.75;

        double result = a + b * c - d + e;

        System.out.println(result);
    }
}
```

Remember operator precedence:

```text
Multiplication and division happen before addition and subtraction.
```

---

# 61. Solutions to the Hard-Level Problems

## 1. int inside Integer

```java
public class Main {
    public static void main(String[] args) {
        int primitive = 100;
        Integer wrapper = primitive;

        System.out.println(wrapper);
    }
}
```

## 2. long inside Long

```java
public class Main {
    public static void main(String[] args) {
        long primitive = 9876543210L;
        Long wrapper = primitive;

        System.out.println(wrapper);
    }
}
```

## 3. double inside Double

```java
public class Main {
    public static void main(String[] args) {
        double primitive = 45.67;
        Double wrapper = primitive;

        System.out.println(wrapper);
    }
}
```

## 4. float inside Float

```java
public class Main {
    public static void main(String[] args) {
        float primitive = 12.5f;
        Float wrapper = primitive;

        System.out.println(wrapper);
    }
}
```

## 5. char inside Character

```java
public class Main {
    public static void main(String[] args) {
        char primitive = 'A';
        Character wrapper = primitive;

        System.out.println(wrapper);
    }
}
```

## 6. boolean inside Boolean

```java
public class Main {
    public static void main(String[] args) {
        boolean primitive = true;
        Boolean wrapper = primitive;

        System.out.println(wrapper);
    }
}
```

## 7. byte inside Byte

```java
public class Main {
    public static void main(String[] args) {
        byte primitive = 100;
        Byte wrapper = primitive;

        System.out.println(wrapper);
    }
}
```

## 8. short inside Short

```java
public class Main {
    public static void main(String[] args) {
        short primitive = 30000;
        Short wrapper = primitive;

        System.out.println(wrapper);
    }
}
```

## 9. Autoboxing

```java
public class Main {
    public static void main(String[] args) {
        int primitive = 500;

        Integer wrapper = primitive;

        System.out.println(wrapper);
    }
}
```

## 10. Unboxing

```java
public class Main {
    public static void main(String[] args) {
        Integer wrapper = 500;

        int primitive = wrapper;

        System.out.println(primitive);
    }
}
```

---

# 62. Combined Demonstration Program

```java
public class Main {
    public static void main(String[] args) {

        // Parsing
        String textNumber = "123";
        int parsedNumber = Integer.parseInt(textNumber);

        // Primitive to String
        String convertedText = String.valueOf(parsedNumber);

        // Implicit casting
        double widenedValue = parsedNumber;

        // Explicit casting
        int narrowedValue = (int) 45.67;

        // Character conversion
        char character = 'A';
        int unicodeValue = character;

        int code = 66;
        char convertedCharacter = (char) code;

        // Type promotion
        byte first = 10;
        byte second = 20;
        int byteSum = first + second;

        double mixedResult = parsedNumber + 5.5;

        // Autoboxing
        Integer wrapperObject = parsedNumber;

        // Unboxing
        int primitiveValue = wrapperObject;

        System.out.println("Parsed number: " + parsedNumber);
        System.out.println("Converted String: " + convertedText);
        System.out.println("Widened value: " + widenedValue);
        System.out.println("Narrowed value: " + narrowedValue);
        System.out.println("Unicode value: " + unicodeValue);
        System.out.println("Converted character: " + convertedCharacter);
        System.out.println("Byte sum: " + byteSum);
        System.out.println("Mixed result: " + mixedResult);
        System.out.println("Wrapper object: " + wrapperObject);
        System.out.println("Unboxed primitive: " + primitiveValue);
    }
}
```

---

# 63. Interview-Level Concept Questions

## Q1. What is parsing?

Parsing converts a String representation into a primitive value.

```java
int number = Integer.parseInt("123");
```

---

## Q2. What is type casting?

Type casting converts a value from one compatible data type into another.

```java
double number = 10;
int value = (int) number;
```

---

## Q3. What is widening casting?

Automatic conversion from a smaller type into a larger compatible type.

```java
int value = 10;
double number = value;
```

---

## Q4. What is narrowing casting?

Manual conversion from a larger type into a smaller type.

```java
double value = 10.5;
int number = (int) value;
```

---

## Q5. Does casting double to int round the value?

No.

It removes the fractional part.

```java
(int) 10.99
```

produces:

```text
10
```

---

## Q6. Why does byte + byte produce int?

Java promotes `byte`, `short`, and `char` operands into `int` before arithmetic.

---

## Q7. What is type promotion?

Automatic conversion of operands into a larger or standard type during expression evaluation.

---

## Q8. What is a wrapper class?

An object-based form of a primitive data type.

Example:

```text
int → Integer
```

---

## Q9. What is autoboxing?

Automatic conversion from primitive to wrapper.

```java
Integer value = 10;
```

---

## Q10. What is unboxing?

Automatic conversion from wrapper to primitive.

```java
Integer object = 10;
int value = object;
```

---

## Q11. What happens when a null wrapper is unboxed?

A `NullPointerException` occurs.

---

## Q12. What is the difference between parseInt() and valueOf()?

```java
Integer.parseInt("123")
```

returns `int`.

```java
Integer.valueOf("123")
```

returns `Integer`.

---

# 64. Final Cheat Sheet

## Parsing

```java
byte a = Byte.parseByte("10");
short b = Short.parseShort("100");
int c = Integer.parseInt("1000");
long d = Long.parseLong("10000");
float e = Float.parseFloat("12.5");
double f = Double.parseDouble("45.67");
boolean g = Boolean.parseBoolean("true");
```

## Primitive to String

```java
String text = String.valueOf(value);
```

## Widening Casting

```java
byte → short → int → long → float → double
char → int → long → float → double
```

Example:

```java
int a = 10;
double b = a;
```

## Narrowing Casting

```java
double → float → long → int → short → byte
```

Example:

```java
double a = 45.67;
int b = (int) a;
```

## Character Conversion

```java
char ch = 'A';
int code = ch;
```

```java
int code = 65;
char ch = (char) code;
```

## Type Promotion

```text
byte, short, char → int
int + long → long
long + float → float
float + double → double
```

## Wrapper Classes

```text
byte    → Byte
short   → Short
int     → Integer
long    → Long
float   → Float
double  → Double
char    → Character
boolean → Boolean
```

## Autoboxing

```java
int primitive = 10;
Integer wrapper = primitive;
```

## Unboxing

```java
Integer wrapper = 10;
int primitive = wrapper;
```

## Most Important Rules

1. Use parsing for `String` to primitive conversion.
2. Use `String.valueOf()` for primitive to String conversion.
3. Smaller to larger numeric conversion is usually automatic.
4. Larger to smaller conversion requires explicit casting.
5. Narrowing may lose data.
6. Casting a decimal into an integer truncates the decimal part.
7. `byte`, `short`, and `char` become `int` during arithmetic.
8. The largest numeric type in an expression usually controls the result.
9. Wrapper classes allow primitive values to be used as objects.
10. Primitive to wrapper is autoboxing.
11. Wrapper to primitive is unboxing.
12. Never unbox a `null` wrapper object.
13. Use `.equals()` to compare wrapper values.
14. Use `f` for float literals and `L` for large long literals.
15. Cast before division when you need a decimal result.

---

# 65. One Final Memory Trick

Remember this sequence:

```text
TEXT → PARSE → NUMBER
NUMBER → CAST → ANOTHER NUMBER TYPE
MIXED NUMBERS → PROMOTION
PRIMITIVE → BOX → WRAPPER
WRAPPER → UNBOX → PRIMITIVE
ANY VALUE → String.valueOf() → STRING
```

This single flow covers almost every beginner problem involving parsing, casting, promotion, and wrapper classes.
