package net.azurewebsites.amazin_online_bookstore;

import net.azurewebsites.amazin_online_bookstore.entity.Book;
import net.azurewebsites.amazin_online_bookstore.entity.Person;
import net.azurewebsites.amazin_online_bookstore.entity.Purchase;
import net.azurewebsites.amazin_online_bookstore.repository.BookRepository;
import net.azurewebsites.amazin_online_bookstore.repository.PersonRepository;
import net.azurewebsites.amazin_online_bookstore.repository.PurchaseRepository;

import net.azurewebsites.amazin_online_bookstore.service.JaccardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TableInitializer implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private JaccardService jaccardService;

    @Override
    public void run(String... args) throws Exception {

        // Persons

        Person person1 = new Person();
        person1.setEmail("jeffbezos@email.com");
        person1.setUsername("owner");
        person1.setPassword("1234");
        person1.setFirstName("Jeff");
        person1.setLastName("Bezos");
        person1.setRole(Person.Role.Employee);
        personRepository.save(person1);

        Person person2 = new Person();
        person2.setEmail("faiazahsan@email.com");
        person2.setUsername("faiaz");
        person2.setPassword("1234");
        person2.setFirstName("Faiaz");
        person2.setLastName("Ahsan");
        personRepository.save(person2);

        Person person3 = new Person();
        person3.setEmail("owenpetersen@email.com");
        person3.setUsername("owen");
        person3.setPassword("1234");
        person3.setFirstName("Owen");
        person3.setLastName("Petersen");
        personRepository.save(person3);

        Person person4 = new Person();
        person4.setEmail("johndoe@email.com");
        person4.setUsername("john");
        person4.setPassword("1234");
        person4.setFirstName("John");
        person4.setLastName("Doe");
        personRepository.save(person4);

        // Books

        Book hobbit = new Book();
        hobbit.setTitle("The Hobbit");
        hobbit.setAuthor("J.R.R. Tolkien");
        hobbit.setIsbn("123456789");
        hobbit.setPublisher("George Allen & Unwin");
        hobbit.setPublishedYear(1937);
        hobbit.setEdition(1);
        hobbit.setGenre("Fantasy");
        hobbit.setInventory(3);
        hobbit.setNumOfPages(310);
        hobbit.setPrice(25.00);
        bookRepository.save(hobbit);

        Book twoCities = new Book();
        twoCities.setTitle("A Tale of Two Cities");
        twoCities.setAuthor("Charles Dickens");
        twoCities.setIsbn("9780582030473");
        twoCities.setPublisher("Charles Dickens");
        twoCities.setPublishedYear(1859);
        twoCities.setEdition(2);
        twoCities.setGenre("Historical");
        twoCities.setInventory(5);
        twoCities.setNumOfPages(350);
        twoCities.setPrice(30.00);
        bookRepository.save(twoCities);

        Book dune = new Book();
        dune.setTitle("Dune");
        dune.setAuthor("Frank Herbert");
        dune.setIsbn("9780441172719");
        dune.setPublisher("Chilton Books");
        dune.setPublishedYear(1965);
        dune.setEdition(1);
        dune.setGenre("Science Fiction");
        dune.setInventory(5);
        dune.setNumOfPages(412);
        dune.setPrice(29.99);
        bookRepository.save(dune);

        Book lotr = new Book();
        lotr.setTitle("The Fellowship of the Ring");
        lotr.setAuthor("J.R.R. Tolkien");
        lotr.setIsbn("9780547928210");
        lotr.setPublisher("George Allen & Unwin");
        lotr.setPublishedYear(1954);
        lotr.setEdition(1);
        lotr.setGenre("Fantasy");
        lotr.setInventory(5);
        lotr.setNumOfPages(423);
        lotr.setPrice(39.99);
        bookRepository.save(lotr);

        Book hailMary = new Book();
        hailMary.setTitle("Project Hail Mary");
        hailMary.setAuthor("Andy Weir");
        hailMary.setIsbn("9780593135204");
        hailMary.setPublisher("Ballantine Books");
        hailMary.setPublishedYear(2021);
        hailMary.setEdition(1);
        hailMary.setGenre("Science Fiction");
        hailMary.setInventory(5);
        hailMary.setNumOfPages(496);
        hailMary.setPrice(27.00);
        bookRepository.save(hailMary);

        Book mockingbird = new Book();
        mockingbird.setTitle("To Kill a Mockingbird");
        mockingbird.setAuthor("Harper Lee");
        mockingbird.setIsbn("9780061120084");
        mockingbird.setPublisher("J.B. Lippincott & Co.");
        mockingbird.setPublishedYear(1960);
        mockingbird.setEdition(1);
        mockingbird.setGenre("Fiction");
        mockingbird.setInventory(5);
        mockingbird.setNumOfPages(281);
        mockingbird.setPrice(24.99);
        bookRepository.save(mockingbird);

        Book gatsby = new Book();
        gatsby.setTitle("The Great Gatsby");
        gatsby.setAuthor("F. Scott Fitzgerald");
        gatsby.setIsbn("9780743273565");
        gatsby.setPublisher("Charles Scribner's Sons");
        gatsby.setPublishedYear(1925);
        gatsby.setEdition(1);
        gatsby.setGenre("Fiction");
        gatsby.setInventory(5);
        gatsby.setNumOfPages(180);
        gatsby.setPrice(19.99);
        bookRepository.save(gatsby);

        Book got = new Book();
        got.setTitle("A Game of Thrones");
        got.setAuthor("George R.R. Martin");
        got.setIsbn("9780553103540");
        got.setPublisher("Bantam Spectra");
        got.setPublishedYear(1996);
        got.setEdition(1);
        got.setGenre("Fantasy");
        got.setInventory(5);
        got.setNumOfPages(694);
        got.setPrice(34.99);
        bookRepository.save(got);

        Book neuromancer = new Book();
        neuromancer.setTitle("Neuromancer");
        neuromancer.setAuthor("William Gibson");
        neuromancer.setIsbn("9780441569595");
        neuromancer.setPublisher("Ace Books");
        neuromancer.setPublishedYear(1984);
        neuromancer.setEdition(1);
        neuromancer.setGenre("Science Fiction");
        neuromancer.setInventory(5);
        neuromancer.setNumOfPages(271);
        neuromancer.setPrice(22.99);
        bookRepository.save(neuromancer);

        Book dragonTattoo = new Book();
        dragonTattoo.setTitle("The Girl with the Dragon Tattoo");
        dragonTattoo.setAuthor("Stieg Larsson");
        dragonTattoo.setIsbn("9780307454546");
        dragonTattoo.setPublisher("Norstedts Förlag");
        dragonTattoo.setPublishedYear(2005);
        dragonTattoo.setEdition(1);
        dragonTattoo.setGenre("Mystery");
        dragonTattoo.setInventory(5);
        dragonTattoo.setNumOfPages(465);
        dragonTattoo.setPrice(25.99);
        bookRepository.save(dragonTattoo);

        Book pride = new Book();
        pride.setTitle("Pride and Prejudice");
        pride.setAuthor("Jane Austen");
        pride.setIsbn("9780141439518");
        pride.setPublisher("T. Egerton, Whitehall");
        pride.setPublishedYear(1813);
        pride.setEdition(1);
        pride.setGenre("Romance");
        pride.setInventory(5);
        pride.setNumOfPages(279);
        pride.setPrice(17.99);
        bookRepository.save(pride);

        Book tfios = new Book();
        tfios.setTitle("The Fault in Our Stars");
        tfios.setAuthor("John Green");
        tfios.setIsbn("9780525478812");
        tfios.setPublisher("Dutton Books");
        tfios.setPublishedYear(2012);
        tfios.setEdition(1);
        tfios.setGenre("Romance");
        tfios.setInventory(5);
        tfios.setNumOfPages(313);
        tfios.setPrice(17.99);
        bookRepository.save(tfios);

        Book sapiens = new Book();
        sapiens.setTitle("Sapiens: A Brief History of Humankind");
        sapiens.setAuthor("Yuval Noah Harari");
        sapiens.setIsbn("9780062316097");
        sapiens.setPublisher("Harvill Secker");
        sapiens.setPublishedYear(2011);
        sapiens.setEdition(1);
        sapiens.setGenre("Non-Fiction");
        sapiens.setInventory(5);
        sapiens.setNumOfPages(443);
        sapiens.setPrice(32.99);
        bookRepository.save(sapiens);

        Book litHistory = new Book();
        litHistory.setTitle("The History of Literature");
        litHistory.setAuthor("John Sutherland");
        litHistory.setIsbn("9780199688364");
        litHistory.setPublisher("Oxford University Press");
        litHistory.setPublishedYear(2013);
        litHistory.setEdition(1);
        litHistory.setGenre("Non-Fiction");
        litHistory.setInventory(5);
        litHistory.setNumOfPages(400);
        litHistory.setPrice(28.99);
        bookRepository.save(litHistory);

        Book geisha = new Book();
        geisha.setTitle("Memoirs of a Geisha");
        geisha.setAuthor("Arthur Golden");
        geisha.setIsbn("9780679781585");
        geisha.setPublisher("Alfred A. Knopf");
        geisha.setPublishedYear(1997);
        geisha.setEdition(1);
        geisha.setGenre("Historical Fiction");
        geisha.setInventory(5);
        geisha.setNumOfPages(448);
        geisha.setPrice(27.99);
        bookRepository.save(geisha);

        Book goneWind = new Book();
        goneWind.setTitle("Gone with the Wind");
        goneWind.setAuthor("Margaret Mitchell");
        goneWind.setIsbn("9781416548942");
        goneWind.setPublisher("Macmillan Publishers");
        goneWind.setPublishedYear(1936);
        goneWind.setEdition(1);
        goneWind.setGenre("Historical Fiction");
        goneWind.setInventory(5);
        goneWind.setNumOfPages(1037);
        goneWind.setPrice(34.99);
        bookRepository.save(goneWind);

        Book shining = new Book();
        shining.setTitle("The Shining");
        shining.setAuthor("Stephen King");
        shining.setIsbn("9780307743657");
        shining.setPublisher("Doubleday");
        shining.setPublishedYear(1977);
        shining.setEdition(1);
        shining.setGenre("Horror");
        shining.setInventory(5);
        shining.setNumOfPages(447);
        shining.setPrice(24.99);
        bookRepository.save(shining);

        Book houseLeaves = new Book();
        houseLeaves.setTitle("House of Leaves");
        houseLeaves.setAuthor("Mark Z. Danielewski");
        houseLeaves.setIsbn("9780375703768");
        houseLeaves.setPublisher("Pantheon Books");
        houseLeaves.setPublishedYear(2000);
        houseLeaves.setEdition(1);
        houseLeaves.setGenre("Horror");
        houseLeaves.setInventory(5);
        houseLeaves.setNumOfPages(709);
        houseLeaves.setPrice(31.99);
        bookRepository.save(houseLeaves);

        Book orientExpress = new Book();
        orientExpress.setTitle("Murder on the Orient Express");
        orientExpress.setAuthor("Agatha Christie");
        orientExpress.setIsbn("9780007119313");
        orientExpress.setPublisher("Collins Crime Club");
        orientExpress.setPublishedYear(1934);
        orientExpress.setEdition(1);
        orientExpress.setGenre("Mystery");
        orientExpress.setInventory(5);
        orientExpress.setNumOfPages(256);
        orientExpress.setPrice(21.99);
        bookRepository.save(orientExpress);

        Book deathOnNile = new Book();
        deathOnNile.setTitle("Death on the Nile");
        deathOnNile.setAuthor("Agatha Christie");
        deathOnNile.setIsbn("9780007119320");
        deathOnNile.setPublisher("Collins Crime Club");
        deathOnNile.setPublishedYear(1937);
        deathOnNile.setEdition(1);
        deathOnNile.setGenre("Mystery");
        deathOnNile.setInventory(5);
        deathOnNile.setNumOfPages(333);
        deathOnNile.setPrice(22.99);
        bookRepository.save(deathOnNile);

        Book it = new Book();
        it.setTitle("It");
        it.setAuthor("Stephen King");
        it.setIsbn("9781501142970");
        it.setPublisher("Viking Press");
        it.setPublishedYear(1986);
        it.setEdition(1);
        it.setGenre("Horror");
        it.setInventory(5);
        it.setNumOfPages(1138);
        it.setPrice(35.99);
        bookRepository.save(it);

        Book androids = new Book();
        androids.setTitle("Do Androids Dream of Electric Sheep?");
        androids.setAuthor("Philip K. Dick");
        androids.setIsbn("9780345404473");
        androids.setPublisher("Doubleday");
        androids.setPublishedYear(1968);
        androids.setEdition(1);
        androids.setGenre("Science Fiction");
        androids.setInventory(5);
        androids.setNumOfPages(210);
        androids.setPrice(23.99);
        bookRepository.save(androids);

        // Purchases

        // Person1: Non-Fiction & Historical Fiction
        Purchase p1 = new Purchase();
        p1.setDateTime(LocalDateTime.of(2024, 1, 2, 10, 15));
        p1.setBuyer(person1);
        p1.setPurchasedBook(sapiens);
        p1.setQuantity(1);
        purchaseRepository.save(p1);

        Purchase p2 = new Purchase();
        p2.setDateTime(LocalDateTime.of(2024, 1, 5, 11, 45));
        p2.setBuyer(person1);
        p2.setPurchasedBook(litHistory);
        p2.setQuantity(1);
        purchaseRepository.save(p2);

        Purchase p3 = new Purchase();
        p3.setDateTime(LocalDateTime.of(2024, 1, 8, 14, 20));
        p3.setBuyer(person1);
        p3.setPurchasedBook(geisha);
        p3.setQuantity(1);
        purchaseRepository.save(p3);

        Purchase p4 = new Purchase();
        p4.setDateTime(LocalDateTime.of(2024, 1, 12, 9, 10));
        p4.setBuyer(person1);
        p4.setPurchasedBook(goneWind);
        p4.setQuantity(1);
        purchaseRepository.save(p4);

        Purchase p5 = new Purchase();
        p5.setDateTime(LocalDateTime.of(2024, 1, 15, 16, 35));
        p5.setBuyer(person1);
        p5.setPurchasedBook(twoCities);
        p5.setQuantity(1);
        purchaseRepository.save(p5);


// Person2: Historical Fiction & Fiction
        Purchase p6 = new Purchase();
        p6.setDateTime(LocalDateTime.of(2024, 2, 1, 13, 55));
        p6.setBuyer(person2);
        p6.setPurchasedBook(geisha);
        p6.setQuantity(1);
        purchaseRepository.save(p6);

        Purchase p7 = new Purchase();
        p7.setDateTime(LocalDateTime.of(2024, 2, 4, 10, 25));
        p7.setBuyer(person2);
        p7.setPurchasedBook(goneWind);
        p7.setQuantity(1);
        purchaseRepository.save(p7);

        Purchase p8 = new Purchase();
        p8.setDateTime(LocalDateTime.of(2024, 2, 8, 15, 40));
        p8.setBuyer(person2);
        p8.setPurchasedBook(mockingbird);
        p8.setQuantity(1);
        purchaseRepository.save(p8);

        Purchase p9 = new Purchase();
        p9.setDateTime(LocalDateTime.of(2024, 2, 12, 11, 5));
        p9.setBuyer(person2);
        p9.setPurchasedBook(gatsby);
        p9.setQuantity(1);
        purchaseRepository.save(p9);

        Purchase p10 = new Purchase();
        p10.setDateTime(LocalDateTime.of(2024, 2, 15, 17, 10));
        p10.setBuyer(person2);
        p10.setPurchasedBook(shining);
        p10.setQuantity(1);
        purchaseRepository.save(p10);


// Person3: Science Fiction
        Purchase p11 = new Purchase();
        p11.setDateTime(LocalDateTime.of(2024, 3, 1, 12, 0));
        p11.setBuyer(person3);
        p11.setPurchasedBook(dune);
        p11.setQuantity(1);
        purchaseRepository.save(p11);

        Purchase p12 = new Purchase();
        p12.setDateTime(LocalDateTime.of(2024, 3, 4, 14, 30));
        p12.setBuyer(person3);
        p12.setPurchasedBook(neuromancer);
        p12.setQuantity(1);
        purchaseRepository.save(p12);

        Purchase p13 = new Purchase();
        p13.setDateTime(LocalDateTime.of(2024, 3, 8, 10, 50));
        p13.setBuyer(person3);
        p13.setPurchasedBook(androids);
        p13.setQuantity(1);
        purchaseRepository.save(p13);

        Purchase p14 = new Purchase();
        p14.setDateTime(LocalDateTime.of(2024, 3, 12, 9, 45));
        p14.setBuyer(person3);
        p14.setPurchasedBook(hailMary);
        p14.setQuantity(1);
        purchaseRepository.save(p14);


// Person4: Science Fiction & Fantasy
        Purchase p15 = new Purchase();
        p15.setDateTime(LocalDateTime.of(2024, 4, 1, 8, 20));
        p15.setBuyer(person4);
        p15.setPurchasedBook(dune);
        p15.setQuantity(1);
        purchaseRepository.save(p15);

        Purchase p16 = new Purchase();
        p16.setDateTime(LocalDateTime.of(2024, 4, 4, 15, 10));
        p16.setBuyer(person4);
        p16.setPurchasedBook(neuromancer);
        p16.setQuantity(1);
        purchaseRepository.save(p16);

        Purchase p17 = new Purchase();
        p17.setDateTime(LocalDateTime.of(2024, 4, 8, 11, 0));
        p17.setBuyer(person4);
        p17.setPurchasedBook(androids);
        p17.setQuantity(1);
        purchaseRepository.save(p17);

        Purchase p18 = new Purchase();
        p18.setDateTime(LocalDateTime.of(2024, 4, 12, 17, 50));
        p18.setBuyer(person4);
        p18.setPurchasedBook(hobbit);
        p18.setQuantity(1);
        purchaseRepository.save(p18);

        Purchase p19 = new Purchase();
        p19.setDateTime(LocalDateTime.of(2024, 4, 15, 13, 25));
        p19.setBuyer(person4);
        p19.setPurchasedBook(lotr);
        p19.setQuantity(1);
        purchaseRepository.save(p19);

        Purchase p20 = new Purchase();
        p20.setDateTime(LocalDateTime.of(2024, 4, 18, 10, 40));
        p20.setBuyer(person4);
        p20.setPurchasedBook(got);
        p20.setQuantity(1);
        purchaseRepository.save(p20);

        jaccardService.UpdateJaccardsForUser(person1);
        jaccardService.UpdateJaccardsForUser(person2);
        jaccardService.UpdateJaccardsForUser(person3);
        jaccardService.UpdateJaccardsForUser(person4);
    }


}

