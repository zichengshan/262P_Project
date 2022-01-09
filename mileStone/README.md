### Milestone 1

- Import org.json by maven:

  ```
  <dependency>
      <groupId>org.json</groupId>
      <artifactId>json</artifactId>
      <version>20180130</version>
  </dependency>
  ```

- XML files sources: https://drive.google.com/drive/folders/1NvFFKEO8z5soWpj6AOdI1ZNEWCcGWGgG?usp=sharing.

- Tasks:

  1. Read an XML file (given as command line argument) into a JSON object and write the JSON object back on disk as a JSON file.
  2. Read an XML file into a JSON object, and extract some smaller sub-object inside, given a certain path (use JSONPointer). Write that smaller object to disk as a JSON file.
  3. Read an XML file into a JSON object, check if it has a certain key path (given in the command line too). If so, save the JSON object to disk; if not, discard it.
  4. Read an XML file into a JSON object, and add the prefix "swe262_" to all of its keys.
  5. Read an XML file into a JSON object, replace a sub-object on a certain key path with another JSON object that you construct, then write the result on disk as a JSON file. 