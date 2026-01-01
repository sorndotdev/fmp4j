---
name: API Integration PR
about: Submit a pull request for a new API integration
title: "Add API support for: /replace-this-with-endpoint"
labels: enhancement
assignees: ''

---

**Instructions**  
This PR should correspond to an [API Integration issue](https://github.com/marketdataapi/fmp4j/issues).
Replace *all* example values with real implementation details. Do **not** leave placeholders or examples unchanged.

---

**Linked Issue:**  
*Reference the corresponding issue number (e.g. #42)*

> Closes #42

**Notes for Reviewers:**  
*Mention any implementation choices, assumptions, or edge cases handled*

> - Your note here 1
> - Your note here 2
> - Your note here 3

**Submission Checklist**
Please verify the following before submitting your changes:

* [ ] Real FMP responses are used in the JSON test fixtures
* [ ] I’ve read and followed the instructions in [CONTRIBUTING.md](../../docs/CONTRIBUTING.md)
* [ ] The endpoint table in [README.md](../../docs/README.md) is updated with ✅ and version info
* [ ] The project builds successfully using:
    * `./gradlew clean build` (for Linux/MacOS)
    * `.\gradlew.bat clean build` (for Windows)

---

**Automatic Checks (do not edit below):**

* [x] Code is tested and maintains 99%+ test coverage
* [x] Commits are prefixed with the issue number
* [x] Code style follows project conventions
* [x] Model serialization files are included