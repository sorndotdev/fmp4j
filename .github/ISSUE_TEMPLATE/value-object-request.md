---
name: Value Object
about: Create a new value object (VO)
title: "New Value Object: FmpNameOfObject"
labels: enhancement
assignees: ''

---

**Instructions**
Replace *all* example values below with your actual implementation details. Do **not** keep the examples as-is. Make
sure to read the full [documentation](https://github.com/marketdataapi/fmp4j/tree/master/docs) before contributing.

---

**Is the value object an FMP parameter or in response body?**
> Yes/No

**(If "yes") FMP Docs Link:**
>

**(If "no") Why is it needed? (be clear and concise)**
>

**Invariants**
>

---

## Requirements

**Value Object**

- [ ] implements `FmpValueObject`
- [ ] consider edge cases (max, min, patterns, ...)
- [ ] implements `equals()`
- [ ] implements `hashCode()`
- [ ] implements `toString()` should return `value()` (or masked value where appropriate)
- [ ] has static factory method matching the value object name
- [ ] has private constructor
- [ ] is final class
- [ ] where appropriate, use enums
- [ ] where appropriate, implements `Comparable` interface

**General**

- [ ] replace *all* existing primitives with the new Value Object in the same PR
