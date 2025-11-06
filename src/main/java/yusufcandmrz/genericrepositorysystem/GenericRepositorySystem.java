package yusufcandmrz.genericrepositorysystem;

public class GenericRepositorySystem {

    public void run() {
        InMemoryRepository<Book, Long> bookRepository = new InMemoryRepository<>();
        bookRepository.save(new Book(1L, "1984", "George Orwell"));
    }
}
