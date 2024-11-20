# Pagable: A Simple Pagable Managing on API

## Description
Pageable is an interface provided by Spring Data JPA that allows you to implement pagination and sorting when querying the database. It helps limit the amount of data returned and manage large datasets efficiently by splitting results into smaller, manageable chunks (pages).

**Matrix Learning**
- [Key Concepts (baeldung.com)](https://www.baeldung.com/spring-data-jpa-pagination-sorting)
- [Page Request](#1)
- [Work Case](#2)

## Page Request
- *PageRequest.of(int page, int size)*
- *PageRequest.of(int page, int size, Sort sort)*

### PageRequest.of(int page, int size):
- [page] : The index of the page (0-based index).
- [size] : The number of items on each page.

### PageRequest.of(int page, int size):
- [page] : The index of the page (0-based index).
- [size] : The number of items on each page.
- [sort] : Sorting pagination request using the Sort object in conjunction with PageRequest.

## Return Types
When using Pageable, Spring Data provides the following result types:
1. `Page<T>` : Contains the list of results for the current page along with metadata such as total pages and total elements.
2. `Slice<T>` : Similar to Page<T> but without the total count of elements, which can be useful for performance.
3. `List<T>` : If you don’t need metadata, you can simply use a list of results.

## Work Case
### * **PageRequest.of(int page, int size)**
1. Repository Definition: In your repository, you can add methods that accept Pageable as a parameter.
``` example

    @Query("select h from ExUser h join h.exDetail")
    List<ExUser> findDetail(Pageable pageable);

```
2. Service Layer: Use Pageable to fetch a specific page of data.
```

        public List<ExUserResponseDto> getDetail(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<ExUser> responseDto = exUserRepository.findDetail(pageable);
        return GenericMapperUtil.toDtoList(responseDto, ExUserResponseDto.class );
    }

```

3. Controller Layer: Use Pageable to mapping out.
```

    @GetMapping("/getDetail")
    public ResponseEntity<?> getDetail(@RequestParam int size,
                                       @RequestParam int page) {
        List<ExUserResponseDto> exUser = exUserService.getDetail(page, size);
        return new ResponseEntity<>(exUser, HttpStatus.OK);
    }

```
### * **PageRequest.of(int page, int size)**
1. Repository Definition: In your repository, you can add methods that accept Pageable as a parameter.
``` example

    @Query("SELECT h FROM ExUser h")
    List<ExUser> findAllCustom(Pageable pageable);

```
2. Service Layer: Use Pageable to fetch a specific page of data.
```

    public List<ExUser> getExUserList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("email").descending());
        return exUserRepository.findAllCustom(pageable);
    }

```

3. Controller Layer: Use Pageable to mapping out.
```

    public ResponseEntity<?> geExUserList(@RequestParam int size,
                                          @RequestParam int page) {
        List<?> exUser = exUserService.getExUserList(page, size);
        return new ResponseEntity<>(exUser, HttpStatus.OK);
    }

```

## Conclusion
`Pageable` in Spring Data JPA is a powerful tool for implementing pagination and sorting in database queries. It improves performance and provides a more manageable way to deal with large datasets. By defining paginated queries in the repository and managing results in your service or controller, you can efficiently handle data for your application.


