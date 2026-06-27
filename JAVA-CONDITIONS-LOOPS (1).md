# Java Foundations: Conditionals & Loops (Detailed Edition)

> Every topic below follows the **same 5-block structure** so it's easy to scan and revise:
> **What it is → When to use → Blueprint (syntax skeleton) → Example → Key points.**
>
> **Java version:** all valid on **Java 21+**. Modern features are tagged with the version they arrived in.

---

# PART 1 — CONDITIONALS

## Decision Order: WHEN TO USE WHAT (read this first)

Ask these questions **in order** and stop at the first "yes":

1. **Do I just need to run something only if a condition is true (and skip otherwise)?**
   → use **`if`**
2. **Are there exactly two outcomes — one for true, one for false?**
   → use **`if-else`**
3. **Am I only choosing a *value* to store, based on a condition?**
   → use the **ternary `?:`**
4. **Are there several mutually exclusive conditions / ranges (e.g. grade bands)?**
   → use an **`if-else-if` ladder**
5. **Am I comparing ONE variable against MANY fixed constant values (1, 2, 3 / "ADMIN", "USER" / enum constants)?**
   → use a **`switch`**
   - Writing new code? → prefer the **arrow `switch`** (safer, no fall-through)
   - Need to branch on the *type* of an object? → use **pattern-matching `switch`**
6. **Does one decision depend on the result of another decision?**
   → use a **nested `if`** (but try to flatten it with `&&` first)

> Rule of thumb: **many values of one variable → `switch`. Many different conditions → `if-else-if`.**

---

## 1. `if` statement

**What it is**
- The most basic decision. Runs a block of code **only when** a condition is `true`.
- If the condition is `false`, the block is skipped entirely and the program continues.

**When to use**
- You want to do something **only in one situation**, and do nothing otherwise.
- Example need: "If the user is logged in, show the dashboard."

**Blueprint**
```java
if (condition) {
    // runs only when condition is true
}
```

**Example**
```java
int age = 20;

if (age >= 18) {
    System.out.println("You are an adult.");
}
```

**Key points**
- The `condition` **must** be a `boolean`. `if (5)` does **not** compile in Java (unlike C/C++).
- Braces `{}` are optional for a single line — but **always write them** to avoid bugs.
- Conditions are built from comparison operators (`==`, `!=`, `<`, `>`, `<=`, `>=`) and logical operators (`&&` and, `||` or, `!` not).

---

## 2. `if-else`

**What it is**
- Two roads. One block runs when the condition is true, the **other** runs when it's false.
- **Exactly one** of the two blocks always executes.

**When to use**
- There are **two opposite outcomes** and you must handle both.
- Example need: "If number is even, print Even; otherwise print Odd."

**Blueprint**
```java
if (condition) {
    // runs when true
} else {
    // runs when false
}
```

**Example**
```java
int number = 7;

if (number % 2 == 0) {
    System.out.println("Even");
} else {
    System.out.println("Odd");
}
```

**Key points**
- The `else` has **no condition** of its own — it's the automatic "otherwise".
- Use this instead of writing two separate `if` statements that test opposite conditions.

---

## 3. `if-else-if` ladder

**What it is**
- A chain of conditions checked **top to bottom**.
- Java runs the **first** block whose condition is true, then **skips all the rest**.
- An optional final `else` catches "none of the above".

**When to use**
- You have **3 or more** mutually exclusive choices.
- The choices depend on **ranges or different conditions** (not one fixed value).
- Example need: grade bands, age categories, score tiers.

**Blueprint**
```java
if (condition1) {
    // runs if condition1 is true
} else if (condition2) {
    // runs if condition1 false AND condition2 true
} else if (condition3) {
    // runs if above false AND condition3 true
} else {
    // runs if none were true
}
```

**Example**
```java
int marks = 82;

if (marks >= 90) {
    System.out.println("Grade A");
} else if (marks >= 75) {
    System.out.println("Grade B");
} else if (marks >= 50) {
    System.out.println("Grade C");
} else {
    System.out.println("Fail");
}
```

**Key points**
- **Order matters.** Put the strictest / highest condition first. If you check `marks >= 50` first, an A-grade student would wrongly match it.
- Once one branch matches, the remaining conditions are **never evaluated**.
- The final `else` is optional — leave it out if there's no default action.

---

## 4. Nested `if`

**What it is**
- An `if` placed **inside** another `if`.
- The inner condition is only checked **if the outer one passed first**.

**When to use**
- A decision is only relevant **after** another decision is already true.
- Example need: "If the user has a ticket, *then* check their age to decide which section."

**Blueprint**
```java
if (outerCondition) {
    if (innerCondition) {
        // runs only if BOTH are true
    } else {
        // outer true, inner false
    }
}
```

**Example**
```java
boolean hasTicket = true;
int age = 16;

if (hasTicket) {
    if (age >= 18) {
        System.out.println("Enter the adult section.");
    } else {
        System.out.println("Enter the kids section.");
    }
}
```

**Key points**
- Deep nesting (3–4 levels) becomes hard to read — this is called "arrow code".
- Often you can **flatten** it. The example above is equivalent to:
  ```java
  if (hasTicket && age >= 18) { ... }
  else if (hasTicket) { ... }
  ```
- Prefer combining conditions with `&&` / `||` over deep nesting when possible.

---

## 5. Ternary / Conditional Operator `?:`

**What it is**
- A **one-line shortcut** for a simple `if-else` that **produces a value**.
- It is an **expression** (gives back a value), not a statement (doesn't just run code).

**When to use**
- You're **assigning a value** based on a single condition.
- Example need: "Set `max` to a if a > b, else to b."
- **Don't** use it just to run statements — that's `if`'s job.

**Blueprint**
```java
type result = (condition) ? valueIfTrue : valueIfFalse;
```

**Example**
```java
int a = 10, b = 20;

int max = (a > b) ? a : b;              // picks a value
System.out.println(max);                // 20

String label = (a % 2 == 0) ? "Even" : "Odd";
```

**Key points**
- Both results must be **compatible types** (both `int`, both `String`, etc.).
- Can be nested (`x > 0 ? "pos" : x < 0 ? "neg" : "zero"`) but gets unreadable fast — use an `if-else-if` ladder for 3+ cases.
- Great for short assignments; bad for complex logic.

---

## 6. `switch` statement (`case`, `default`)

**What it is**
- A clean way to compare **one variable** against **many fixed constant values**.
- Each possible value is a `case`. `default` handles anything not listed.

**When to use**
- One variable, many **exact** values to match (menu options, day numbers, status codes).
- Cleaner and faster to read than a long `if-else-if` ladder of `==` checks.

**Blueprint** (classic colon style)
```java
switch (variable) {
    case value1:
        // code
        break;
    case value2:
        // code
        break;
    default:
        // code if nothing matched
}
```

**Example**
```java
int day = 3;

switch (day) {
    case 1:
        System.out.println("Monday");
        break;
    case 2:
        System.out.println("Tuesday");
        break;
    case 3:
        System.out.println("Wednesday");
        break;
    default:
        System.out.println("Other day");
}
```

**Key points**
- Allowed types: `byte`, `short`, `char`, `int` (+ wrappers), `String`, `enum`.
- **Not** allowed: `long`, `float`, `double`, `boolean`.
- `case` labels must be **constants** (literals or `final`), never a variable or a range.
- `default` is optional and usually written last (but can go anywhere).

---

## 7. `break` inside `switch`

**What it is**
- `break` **exits the switch immediately** — "I'm done, stop here."
- It prevents the program from continuing into the next case.

**When to use**
- At the end of **every** case in a classic switch, when you want each case to be independent.

**Blueprint**
```java
case value:
    // code
    break;     // stops here, does NOT run the next case
```

**Example**
```java
char grade = 'B';

switch (grade) {
    case 'A':
        System.out.println("Excellent");
        break;
    case 'B':
        System.out.println("Good");
        break;    // <- stops; case 'C' is never reached
    case 'C':
        System.out.println("Average");
        break;
}
```

**Key points**
- Forgetting `break` causes **fall-through** (next topic) — a very common bug.
- The modern arrow `switch` (topic 10) removes the need for `break` entirely.

---

## 8. Fall-Through behavior

**What it is**
- If a case has **no `break`**, execution "falls through" and **keeps running the next case(s)** until it hits a `break` or the end of the switch.
- Can be an accidental bug, **or** an intentional way to group cases that share the same action.

**When to use (intentionally)**
- Multiple values should trigger the **same** code (e.g. months 12, 1, 2 → "Winter").

**Blueprint** (intentional grouping)
```java
switch (variable) {
    case A:
    case B:
    case C:
        // shared code for A, B, and C
        break;
}
```

**Example**
```java
int month = 4;

switch (month) {
    case 12:
    case 1:
    case 2:
        System.out.println("Winter");
        break;
    case 3:
    case 4:
    case 5:
        System.out.println("Spring");   // month 4 lands here
        break;
    default:
        System.out.println("Other season");
}
```

**Key points**
- Stacking cases with **no code between them** = they all share the next block.
- A **missing** `break` where you didn't want fall-through is one of the most common beginner bugs.
- Arrow `switch` (topic 10) does **not** fall through — much safer.

---

## 9. `switch` with `String` and `enum`

**What it is**
- `switch` also works on **`String`** values (since Java 7) and on **`enum`** constants.
- Strings are compared internally using `.equals()`.

**When to use**
- Matching against known text values (roles, commands, statuses).
- Matching against a fixed set of named constants (an `enum` is the cleanest choice for this).

**Blueprint** (String)
```java
switch (text) {
    case "VALUE1": /* code */ break;
    case "VALUE2": /* code */ break;
    default:       /* code */
}
```

**Blueprint** (enum — use the constant name only, NOT EnumName.CONSTANT)
```java
switch (enumValue) {
    case CONSTANT_A: /* code */ break;
    case CONSTANT_B: /* code */ break;
}
```

**Example**
```java
// String switch
String role = "ADMIN";
switch (role) {
    case "ADMIN": System.out.println("Full access");    break;
    case "USER":  System.out.println("Limited access"); break;
    default:      System.out.println("No access");
}

// Enum switch
enum Day { MONDAY, TUESDAY, WEDNESDAY }
Day d = Day.TUESDAY;
switch (d) {
    case MONDAY:    System.out.println("Start of week"); break;
    case TUESDAY:   System.out.println("Second day");    break;
    case WEDNESDAY: System.out.println("Midweek");       break;
}
```

**Key points**
- A `null` String in an **old-style** switch throws `NullPointerException`. (Fixed by `case null` — topic 13.)
- In enum cases, writing `case Day.MONDAY` is a **compile error** — use just `case MONDAY`.
- String cases are **case-sensitive**: `"admin"` ≠ `"ADMIN"`.

---

## 10. Enhanced `switch` / switch expressions (arrow syntax) — *Java 14+*

**What it is**
- The **modern** switch using `->` instead of `:`.
- **No fall-through** — each arrow handles exactly one case, so **no `break` needed**.
- Can act as a **statement** (does something) or an **expression** (returns a value).

**When to use**
- In **all new code** — it's shorter and removes the fall-through bug class.
- Especially when you want the switch to **produce a value** to assign.

**Blueprint** (statement)
```java
switch (variable) {
    case A -> statement;
    case B -> statement;
    default -> statement;
}
```

**Blueprint** (expression — returns a value; note the final semicolon)
```java
type result = switch (variable) {
    case A -> value;
    case B -> value;
    default -> value;
};
```

**Example**
```java
int day = 3;

// As an expression (returns a value)
String name = switch (day) {
    case 1 -> "Monday";
    case 2 -> "Tuesday";
    case 3 -> "Wednesday";
    default -> "Other day";
};
System.out.println(name);   // Wednesday

// Group multiple labels with commas
String type = switch (day) {
    case 1, 2, 3, 4, 5 -> "Weekday";
    case 6, 7          -> "Weekend";
    default            -> "Invalid";
};
```

**Key points**
- No `break` required — the arrow handles one case and stops.
- As an **expression**, it must be **exhaustive**: every possible value must be covered (usually with `default`). The compiler enforces this.
- Combine values with commas instead of stacking cases.

---

## 11. The `yield` keyword — *Java 14+*

**What it is**
- A way to **return a value** out of a switch **expression** when a case needs **multiple lines** (a block `{}`).
- Think of it as "the value this case produces."

**When to use**
- A case in a switch *expression* needs to do some work (a few statements) before deciding its value.

**Blueprint**
```java
type result = switch (variable) {
    case A -> simpleValue;          // single value: no yield needed
    case B -> {
        // multiple statements
        yield computedValue;        // block must produce a value via yield
    }
    default -> defaultValue;
};
```

**Example**
```java
int score = 85;

String grade = switch (score / 10) {
    case 10, 9 -> "A";
    case 8 -> {
        System.out.println("Calculating...");
        yield "B";                  // value produced by this block
    }
    case 7 -> "C";
    default -> "F";
};
```

**Key points**
- `yield` is **only** for switch *expressions* (the value-returning kind).
- It is **not** `return` — `yield` exits the **switch**, while `return` would exit the whole **method**.
- A single-expression arrow (`case 7 -> "C";`) returns its value automatically — no `yield` needed there.

---

## 12. Pattern Matching for `switch` + guarded patterns — *Java 21*

**What it is**
- Lets you switch on the **type** of an object. Java checks the type **and** casts it for you automatically.
- Replaces messy `if (x instanceof Type t)` chains.
- **Guarded patterns** add an extra condition using the `when` keyword.

**When to use**
- You have an `Object` (or a parent type) and need different logic depending on its **actual type**.
- You need both a type check **and** a value condition (use `when`).

**Blueprint** (type patterns)
```java
type result = switch (obj) {
    case TypeA a -> /* use a */;
    case TypeB b -> /* use b */;
    default      -> /* fallback */;
};
```

**Blueprint** (guarded — extra condition with `when`)
```java
case TypeX x when (condition) -> /* runs if type matches AND condition true */;
```

**Example**
```java
Object obj = 42;

String result = switch (obj) {
    case Integer i when i > 100 -> "Big integer";
    case Integer i when i > 0   -> "Small positive integer";
    case Integer i              -> "Zero or negative integer";
    case String s               -> "String of length " + s.length();
    default                     -> "Unknown type";
};
```

**Key points**
- **Order matters:** more specific / guarded cases must come **before** general ones. A general case before a specific one causes a "dominated case" compile error.
- `when` is the guard keyword — only meaningful inside switch patterns.
- Pattern switches must be **exhaustive** (cover all types or include `default`).

---

## 13. `case null` in `switch` — *Java 21*

**What it is**
- Lets you handle a `null` value **inside** the switch instead of crashing.
- Before this, a `null` selector always threw `NullPointerException`.

**When to use**
- The variable being switched on **could be `null`**, and you want to handle that case gracefully without a separate `if (x == null)` guard.

**Blueprint**
```java
switch (variable) {
    case null  -> /* handle null */;
    case A     -> /* ... */;
    default    -> /* ... */;
}
// or combine null with default:
case null, default -> /* handle null AND anything unmatched */;
```

**Example**
```java
String input = null;

String result = switch (input) {
    case null  -> "No value provided";
    case "YES" -> "Confirmed";
    case "NO"  -> "Declined";
    default    -> "Unknown";
};
System.out.println(result);   // No value provided
```

**Key points**
- Without `case null`, a `null` selector **still** throws `NullPointerException`.
- `case null, default` is a handy combo: treat null the same as "everything else".

---

## 14. Record / deconstruction patterns in `switch` — *Java 21*

**What it is**
- A **record** is a compact, immutable data-carrying class.
- In a switch you can **deconstruct** it — pull its fields directly into variables in the `case`.
- These patterns **nest**, so you can destructure records inside records.

**When to use**
- You're working with record data and want its fields **immediately**, without calling getters.
- Common in modern backend / data-modelling code.

**Blueprint**
```java
record Point(int x, int y) {}

switch (obj) {
    case Point(int x, int y) -> /* x and y are ready to use */;
    default                  -> /* ... */;
}
```

**Example**
```java
record Point(int x, int y) {}
record Line(Point start, Point end) {}

Object obj = new Line(new Point(0, 0), new Point(5, 5));

String result = switch (obj) {
    // nested deconstruction; var lets Java infer the field types
    case Line(Point(var x1, var y1), Point(var x2, var y2)) ->
        "Line from (" + x1 + "," + y1 + ") to (" + x2 + "," + y2 + ")";
    case Point(int x, int y) -> "Point at " + x + "," + y;
    default -> "Unknown shape";
};
```

**Key points**
- `var` infers each field's type — convenient inside deconstruction.
- Combine with guards: `case Point(int x, int y) when x == y -> "On the diagonal"`.
- This is **advanced** — get comfortable with topics 1–11 first, then this clicks easily.

---

# PART 2 — LOOPS

## Decision Order: WHEN TO USE WHAT (read this first)

Ask these in order and stop at the first "yes":

1. **Do I know exactly how many times to repeat?**
   → use **`for`** (counter-controlled)
2. **Do I need to repeat *while* a condition holds, but the count is unknown?**
   → use **`while`**
3. **Must the body run at least once before checking the condition?**
   → use **`do-while`**
4. **Am I going through every element of an array / collection and don't need the index?**
   → use the **enhanced `for` (for-each)**
5. **Am I reading input until a special "stop" value appears?**
   → use **`while`** (sentinel-controlled)
6. **Do I need to run continuously until something breaks out?**
   → use an **infinite loop** (`while(true)`) with a `break`

> Quick mental model: **count known → `for`. Condition-based → `while`. At-least-once → `do-while`.**

---

## 1. `for` Loop

**What it is**
- A loop that bundles **start, stop, and step** into one line.
- Ideal when the **number of repetitions is known**.

**When to use**
- You can say "repeat N times" or "go from X to Y".
- Counting, iterating by index, building tables.

**Blueprint**
```java
for (initialization; condition; update) {
    // body runs while condition is true
}
```

**Example**
```java
for (int i = 1; i <= 5; i++) {
    System.out.println("Count: " + i);
}
// 1 2 3 4 5
```

**Flow (memorize this)**
- Run **initialization** once.
- Check **condition** → if false, stop.
- Run **body**.
- Run **update**.
- Go back to checking the condition.

**Key points**
- The loop variable `i` exists **only inside** the loop.
- All three parts are optional; `for (;;) {}` is an infinite loop.

---

## 2. `while` Loop

**What it is**
- Repeats **as long as** a condition stays true.
- The condition is checked **before** each pass (entry-controlled / pre-test).

**When to use**
- You **don't know** the number of repetitions in advance.
- "Keep going until something changes."

**Blueprint**
```java
while (condition) {
    // body
    // update something so the loop can eventually stop
}
```

**Example**
```java
int i = 1;
while (i <= 5) {
    System.out.println(i);
    i++;        // must update, or it loops forever
}
```

**Key points**
- If the condition is false at the start, the body runs **zero** times.
- **You** are responsible for updating the condition inside the body — forgetting this is the #1 cause of infinite loops.

---

## 3. `do-while` Loop

**What it is**
- Like `while`, but the condition is checked **after** the body (exit-controlled / post-test).
- Therefore the body **always runs at least once**.

**When to use**
- You must do something **once before** deciding whether to repeat.
- Classic case: show a menu, then ask "again?".

**Blueprint**
```java
do {
    // body — always runs at least once
} while (condition);   // <- semicolon is REQUIRED here
```

**Example**
```java
int i = 10;
do {
    System.out.println(i);   // prints 10 even though condition is false
    i++;
} while (i <= 5);
```

**Key points**
- Note the **semicolon** after `while(...)`.
- Use it only when "run once first" is genuinely needed — otherwise a plain `while` is clearer.

---

## 4. Enhanced `for` Loop (for-each) — *Java 5+*

**What it is**
- The cleanest way to visit **every element** of an array or collection.
- No counter, no index — Java handles the iteration for you.

**When to use**
- You want to process all elements and **don't need the index**.
- You're **not** removing or restructuring the collection while looping.

**Blueprint**
```java
for (ElementType element : arrayOrCollection) {
    // use element
}
```

**Example**
```java
int[] numbers = {10, 20, 30, 40};
for (int n : numbers) {
    System.out.println(n);
}

List<String> names = List.of("Asha", "Ben", "Cara");
for (String name : names) {
    System.out.println(name);
}
```

**Key points**
- Read it as "**for each** `n` **in** `numbers`".
- You **can't** get the index, and you generally **can't** modify the collection's structure (e.g. remove items) mid-loop — that throws `ConcurrentModificationException`.
- Need the index or removal? Use a classic `for` loop or an `Iterator`.

---

## 5. Nested Loops

**What it is**
- A loop **inside** another loop.
- The **inner** loop runs completely for **each single step** of the **outer** loop.

**When to use**
- Working with grids, matrices, tables, or printing 2D patterns.
- Comparing every item with every other item.

**Blueprint**
```java
for (outer condition) {
    for (inner condition) {
        // runs (outer count × inner count) times total
    }
}
```

**Example**
```java
for (int row = 1; row <= 3; row++) {
    for (int col = 1; col <= 3; col++) {
        System.out.print("* ");
    }
    System.out.println();    // new line after each row
}
/*
* * *
* * *
* * *
*/
```

**Key points**
- Total iterations = outer × inner — watch performance on large data.
- Give inner and outer loops **different variable names** (`i`/`j` or `row`/`col`).

---

## 6. `break`

**What it is**
- **Stops the loop immediately** and jumps to the code after it.
- Remaining iterations are skipped.

**When to use**
- You found what you were looking for and don't need to keep looping.
- An error or stop condition occurs mid-loop.

**Blueprint**
```java
for (...) {
    if (stopCondition) {
        break;     // exit the loop right now
    }
}
```

**Example**
```java
for (int i = 1; i <= 10; i++) {
    if (i == 5) {
        break;
    }
    System.out.println(i);
}
// 1 2 3 4
```

**Key points**
- `break` only exits the **innermost** loop (unless you use a label — topic 8).
- Also used inside `switch` to end a case.

---

## 7. `continue`

**What it is**
- **Skips the rest of the current iteration** and jumps straight to the next one.
- The loop keeps running.

**When to use**
- You want to **ignore certain values** but continue processing the rest.
- Example: skip even numbers, skip blank input lines.

**Blueprint**
```java
for (...) {
    if (skipCondition) {
        continue;   // jump to the next iteration
    }
    // this code is skipped when skipCondition is true
}
```

**Example**
```java
for (int i = 1; i <= 5; i++) {
    if (i == 3) {
        continue;   // skip printing 3
    }
    System.out.println(i);
}
// 1 2 4 5
```

**Key points**
- **`break` vs `continue`:** `break` **stops** the whole loop; `continue` **skips one round** and carries on.
- In a `for` loop, `continue` still runs the **update** part (`i++`) before the next check.

---

## 8. Labeled `break` & `continue`

**What it is**
- By default `break`/`continue` only affect the **innermost** loop.
- A **label** (a name + `:` before a loop) lets you target an **outer** loop from inside a nested one.

**When to use**
- You're deep inside nested loops and need to break out of (or continue) an **outer** loop in one go.

**Blueprint**
```java
labelName:
for (outer) {
    for (inner) {
        break labelName;      // exits the OUTER (labeled) loop
        // or
        continue labelName;   // continues the OUTER (labeled) loop
    }
}
```

**Example**
```java
outer:
for (int i = 1; i <= 3; i++) {
    for (int j = 1; j <= 3; j++) {
        if (i * j == 4) {
            break outer;      // breaks BOTH loops at once
        }
        System.out.println(i + " x " + j);
    }
}
```

**Key points**
- Solves the classic problem: a plain `break` inside nested loops only escapes the inner one.
- Use **sparingly** — too many labels make code hard to follow.

---

## 9. Counter-Controlled Loops

**What it is**
- A **pattern** (not a keyword): the loop repeats a **known, fixed number of times** using a counter.
- Also called **definite iteration**.
- A `for` loop is the natural fit.

**When to use**
- You know the exact count **before** the loop starts.
- Example: sum the first 5 numbers, print a table of 10 rows.

**Blueprint**
```java
for (int counter = start; counter <= end; counter++) {
    // runs a known number of times
}
```

**Example**
```java
int sum = 0;
for (int i = 1; i <= 5; i++) {   // exactly 5 iterations
    sum += i;
}
System.out.println("Sum = " + sum);   // 15
```

**Key points**
- You explicitly control **start, end, and step**.
- "Counter-controlled" just describes the *style* — it's implemented with a normal `for` (or `while`).

---

## 10. Sentinel-Controlled Loops

**What it is**
- A **pattern**: the loop repeats an **unknown** number of times and stops only when it meets a special **sentinel value** (a marker meaning "stop").
- Also called **indefinite iteration**.
- A `while` loop fits naturally.

**When to use**
- Reading input "until the user signals they're done".
- The count isn't known — it depends on the data / user.

**Blueprint**
```java
read first value;
while (value != SENTINEL) {
    // process value
    read next value;
}
```

**Example**
```java
import java.util.Scanner;

Scanner sc = new Scanner(System.in);
int total = 0;

System.out.println("Enter numbers (-1 to stop):");
int input = sc.nextInt();
while (input != -1) {       // -1 is the sentinel
    total += input;
    input = sc.nextInt();   // read the next value
}
System.out.println("Total = " + total);
```

**Key points**
- The sentinel must be a value that **can't appear as real data** (here, `-1` for positive numbers).
- Read **once before** the loop, then **again at the end** of each pass — a common, important pattern.

---

## 11. Infinite Loops

**What it is**
- A loop whose condition is **never false**, so it runs forever — until something inside `break`s out (or the program is killed).

**When to use (intentionally)**
- Servers, game loops, event loops, or menu systems that run "until the user quits".

**Blueprint**
```java
while (true) {
    // ...
    if (exitCondition) {
        break;       // the controlled way out
    }
}

// equivalent shorthand:
for (;;) {
    // ...
}
```

**Example**
```java
int i = 1;
while (true) {
    System.out.println(i);
    if (i == 5) {
        break;       // exit condition
    }
    i++;
}
```

**Key points**
- **Intentional** infinite loops are normal and useful — they just need a `break` or an external stop.
- **Accidental** infinite loops are bugs, usually from a forgotten update (`i++`) or a condition that never becomes false. If your program hangs, suspect this first.

---

# MASTER SUMMARY

## Conditionals — pick the right tool

| Situation | Use |
|---|---|
| Run code only if true, skip otherwise | `if` |
| Two opposite outcomes | `if-else` |
| Choose a *value* from one condition | ternary `?:` |
| Several conditions / ranges | `if-else-if` ladder |
| One variable vs many fixed values | `switch` |
| Modern, fall-through-free switch | arrow `switch` (Java 14+) |
| Multi-line case returning a value | `yield` (Java 14+) |
| Branch on an object's type | pattern-matching `switch` (Java 21) |
| Handle a null safely in switch | `case null` (Java 21) |
| Extract record fields in a case | record patterns (Java 21) |

## Loops — pick the right tool

| Situation | Use |
|---|---|
| Known number of repetitions | `for` (counter-controlled) |
| Repeat while a condition holds | `while` |
| Must run at least once | `do-while` |
| Visit every element, no index | enhanced `for` (for-each) |
| Stop on a special marker value | `while` (sentinel-controlled) |
| Run until told to stop | infinite loop + `break` |

## Control keywords

- **`break`** → leave the loop / switch now.
- **`continue`** → skip the current iteration, keep looping.
- **`label:` + `break label` / `continue label`** → control an *outer* loop from inside a nested one.
- **`yield`** → return a value out of a switch *expression*.

## Two distinctions to never forget

- **`break` stops** the loop; **`continue` skips** one round and continues.
- **`while`** checks the condition **before** the body (may run 0 times); **`do-while`** checks **after** (always runs ≥ 1 time).

---

*Learning order suggestion: master `if` → `if-else` → ladder → `switch` → `for` → `while` → `do-while` → for-each first. Those carry 90% of everyday code. Then layer on arrow switch and the Java 21 pattern features once the basics are automatic.*
