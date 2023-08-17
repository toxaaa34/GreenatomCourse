import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // Задача 1
        //Подключаемся к БД
        System.out.println("1 задание:");
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:gadb.s3db";
            conn = DriverManager.getConnection(url);
            System.out.println("Успешное подключение к БД");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        //создание таблицы

        Statement statmt = conn.createStatement();
        statmt.execute("CREATE TABLE if not exists 'workers' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'surname' VARCHAR(20), 'experience' INTEGER);");

        //заполнение таблицы
        //statmt.execute("INSERT INTO 'workers' ('surname', 'experience') VALUES ('Ivanov', 10); ");
        //statmt.execute("INSERT INTO 'workers' ('surname', 'experience') VALUES ('Petrov', 12); ");
        //statmt.execute("INSERT INTO 'workers' ('surname', 'experience') VALUES ('Sidorov', 14); ");
        //statmt.execute("INSERT INTO 'workers' ('surname', 'experience') VALUES ('Osipov', 12); ");
        //statmt.execute("INSERT INTO 'workers' ('surname', 'experience') VALUES ('Kalashnikov', 16); ");
        //statmt.execute("INSERT INTO 'workers' ('surname', 'experience') VALUES ('Bukin', 8); ");
        //statmt.execute("INSERT INTO 'workers' ('surname', 'experience') VALUES ('Levit', 14); ");

        //Запрос
        ResultSet resSet = statmt.executeQuery("SELECT surname FROM workers ORDER BY experience DESC LIMIT 1,1");

        // или
        // select surname from (select * from workers
        //where experience not in (select max(experience) from workers)
        //order by experience desc) limit 1

        //Вывод результата
        String  surname = resSet.getString("surname");
        System.out.println(surname);

        conn.close();
        statmt.close();
        resSet.close();
        System.out.println();

        // Задача 2, первый вариант
        System.out.println("2 задание:");
        int a = 5, b = 7;
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("a = " + a + ", b = " + b);

        // Задача 2, второй вариант
        a = 5;
        b = 7;
        a = swapNumbers(b, b = a);
        System.out.println("a = " + a + ", b = " + b);
        System.out.println();

        // Задача 3.1
        System.out.println("3.1 задание:");
        int[] keys = { 1, 2, 3, 4, 5, 6 };

        LinkList head = null;
        for (int i = 0; i < keys.length; i++) {
            head = new LinkList(keys[i], head);
        }
        printList(head);
        head = reverseList(head);
        printList(head);
        System.out.println();

        // Задача 3.2
        System.out.println("3.2 задание:");
        String word = "Anton";
        char[] wordArray = word.toLowerCase().toCharArray();
        System.out.println(isPalindrom(wordArray));
    }

    // метод для задачи 2
    public static int swapNumbers(int num1, int num2) {
        return num1;
    }

    // Метод для вывода списка для задачи 3.1
    public static void printList(LinkList head)
    {
        LinkList ptr = head;
        while (ptr != null)
        {
            System.out.print(ptr.data + " —> ");
            ptr = ptr.next;
        }

        System.out.println("null");
    }

    // метод для инвертирования односвязного списка для задачи 3.1
    public static LinkList reverseList(LinkList head)
    {
        LinkList previous = null;
        LinkList current = head;

        while (current != null)
        {
            LinkList next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        return previous;
    }


    // метод для задачи 3.2
    public static boolean isPalindrom(char[] word) {
        int i = 0;
        int j = word.length - 1;
        while (j > i) {
            if (word[i] != word[j]) return false;
            i++;
            j--;
        }
        return true;
    }


}

