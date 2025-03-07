# Respostas - Lab 3.1

## a) Exemplos de AssertJ usando expressive method chaining

### Exemplo 1:
```java
assertThat(found).isNotNull()
    .extracting(Employee::getName)
    .isEqualTo("alex");
```
### Exemplo 2:
```java
assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());
```

### Exemplo 3:
```java
assertThat(found).extracting(Employee::getName).containsOnly("bob");
```

## b) Take note of transitive annotations included in @DataJpaTest.

