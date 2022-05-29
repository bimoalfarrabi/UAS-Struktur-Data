import java.util.Scanner;

class node {

  node left;
  node right;
  String name;
  String number;
  String email;

  public node(String name, String no, String email) {
    left = null;
    right = null;
    this.name = name;
    this.number = no;
    this.email = email;
  }
}

class BinaryTree {

  Scanner sc = new Scanner(System.in);
  public static node root;
  String name;
  String no;
  String email;
  private char s;
  int ch;

  BinaryTree() {
    root = null;
  }

  int flag = 0;

  String checknumber(String number) {
    while (number.length() != 12) {
      System.out.println(
        "\nNomor yang disimpan harus berjumlah 12 digit!...\n"
      );
      System.out.println("TOLONG MASUKAN NOMOR YANG TEPAT!...");
      number = sc.next();
    }
    return number;
  }

  public void create() {
    Scanner sc = new Scanner(System.in);
    int ch, flag = 0, flag1 = 0;
    node ptr, any;
    do {
      System.out.println("\nMasukan nama: ");
      name = sc.next();
      System.out.println("\nMasukan nomor telepon: ");
      no = sc.next();
      no = checknumber(no);
      any = root;
      while (any != null) {
        if (any.number.compareTo(no) == 0) {
          flag = 1;
          break;
        }
        if ((any.number).compareTo(no) > 0) {
          any = any.left;
        } else {
          any = any.right;
        }
      }
      if (flag == 1) {
        System.out.println("Nomor ini sudah tersimpan!...");
        System.out.println("Masukan nomor yang berbeda!...");
        no = sc.next();
        no = checknumber(no);
      }
      System.out.println("\nMasukan alamat Email : ");
      email = sc.next();
      int m = email.length() - 1;
      while (m != -1) {
        if (email.charAt(m) == '@') {
          flag1 = 1;
        }
        if (email.charAt(m) == '.') {
          flag1 = 2;
          break;
        }
        m--;
      }
      if (flag1 != 2) {
        System.out.println("Masukan alamat Email yang benar :");
        email = sc.next();
      }
      node temp = new node(name, no, email);
      if (root == null) {
        root = temp;
      } else {
        ptr = root;
        while (flag == 0) {
          if ((temp.name).compareTo(ptr.name) > 0) {
            if (ptr.right != null) {
              ptr = ptr.right;
            } else {
              ptr.right = temp;
              flag = 1;
            }
          }
          if ((temp.name).compareTo(ptr.name) < 0) {
            if (ptr.left != null) {
              ptr = ptr.left;
            } else {
              ptr.left = temp;
              flag = 1;
            }
          }
        }
      }
      flag = 0;

      System.out.println("");
      System.out.println("Apakah anda ingin menambahkan nomor lain (0/1): ");
      ch = sc.nextInt();
    } while (ch != 0);
  } // create()

  public void insert() {
    create();
  }

  public void update() {
    int flag = 0;
    node ptr;
    System.out.println("Masukan nama dari kontak yang ingin anda perbaharui: ");
    Scanner scan = new Scanner(System.in);
    String key = scan.next();
    ptr = root;
    while (ptr != null) {
      if ((ptr.name).compareTo(key) == 0) {
        flag = 1;
        break;
      }
      if ((ptr.name).compareTo(key) > 0) {
        ptr = ptr.left;
      } else {
        ptr = ptr.right;
      }
    }
    if (flag == 1) {
      System.out.println("Masukan nomor baru : ");
      String new1;
      new1 = sc.next();
      ptr.number = new1;
    }
  }

  public void search() {
    int flag = 0;
    node ptr;
    System.out.println("Masukan nama kontak: ");
    String key = sc.next();
    ptr = root;
    while (ptr != null) {
      if (ptr.name.compareTo(key) == 0) {
        flag = 1;
        System.out.println("\n\t-----DATA DITEMUKAN-----");

        //   System.out.println("\n\t-------");

        System.out.println("-----------------------------------------------");
        System.out.println("NAMA\t|  NOMOR KONTAK  |\tEMAIL");
        System.out.println("-----------------------------------------------");
        System.out.println(
          "" + ptr.name + "\t    " + ptr.number + "\t      " + ptr.email
        );

        //System.out.println("\t----------------------------------------------------------------------------------------");

        break;
      }
      if ((ptr.name).compareTo(key) > 0) {
        ptr = ptr.left;
      } else {
        ptr = ptr.right;
      }
    }
    if (flag == 0) {
      System.out.println("-----DATA TIDAK DITEMUKAN-----");
    }
  }

  public void delete() {
    int flag = 0;
    node ptr, parent = null;
    System.out.println("Masukan nama dari kontak yang ingin anda hapus");
    Scanner scan = new Scanner(System.in);
    String key = scan.next();
    ptr = root;
    while (ptr != null) {
      if ((ptr.name).compareTo(key) == 0) {
        flag = 1;
        break;
      }
      if ((ptr.name).compareTo(key) > 0) {
        parent = ptr;
        ptr = ptr.left;
      } else {
        parent = ptr;
        ptr = ptr.right;
      }
    }
    System.out.println(ptr.name);
    if (flag == 1) {
      if (ptr.left != null && ptr.right == null) {
        if (parent.left == ptr) {
          parent.left = ptr.left;
        } else {
          parent.right = ptr.left;
        }
      }
      if (ptr.left == null && ptr.right != null) {
        if (parent.left == ptr) {
          parent.left = ptr.right;
        } else {
          parent.right = ptr.right;
        }
      }
      if (ptr.left != null && ptr.right != null) {
        node p;
        p = ptr.left;
        System.out.println("ptr= " + ptr.name);
        while (p.right != null) {
          parent = p;
          p = p.right;
        }
        ptr.name = p.name;
        ptr.number = p.number;
        if (p.left != null) {
          parent.right = p.left;
        } else {
          ptr.left = null;
        }
      }
      if (ptr.left == null && ptr.right == null) {
        if (parent.left == ptr) {
          parent.left = null;
        } else {
          parent.right = null;
        }
      }
    }
  }

  public void inorder(node root) {
    if (root != null) {
      inorder(root.left);
      // System.out.println("\n\t-------------------------------------------------------------------------------------");

      //System.out.println("\tName\tContact number\tEmail");

      System.out.println(
        "" + root.name + "\t    " + root.number + "\t      " + root.email
      );

      //   System.out.println("\t----------------------------------------------------------------------------------------");

      inorder(root.right);
    }
  }

  public void display() {
    inorder(root);
  }
}

public class CleverContact {

  public static void main(String s[]) {
    BinaryTree b = new BinaryTree();
    Scanner sc = new Scanner(System.in);
    int ch, ch1;
    System.out.print("\n*****PHONEBOOK***\n");

    do {
      //   System.out.println("\n-------------------------------------------------------------------------------------");
      System.out.print(
        "\n 1. Cari Kontak             \n 2. Masukan Kontak Baru \n 3. Tampilkan Informasi Semua Kontak \n 4. Hapus Kontak \n 5. Perbaharui Kontak \n 6. Keluar \n"
      );
      //     System.out.println("\n-------------------------------------------------------------------------------------");
      System.out.println("MASUKAN PILIHAN ANDA : ");
      ch = sc.nextInt();
      switch (ch) {
        case 1:
          b.search();
          break;
        case 2:
          b.create();
          break;
        case 3:
          System.out.println("-----------------------------------------------");
          System.out.println("NAMA\t|  NOMOR KONTAK  |\tEMAIL");
          System.out.println("-----------------------------------------------");
          b.display();
          System.out.println("-----------------------------------------------");
          break;
        case 4:
          b.delete();
          break;
        case 5:
          b.update();
          break;
        case 6:
          System.out.println("Sedang Keluar Mohon Tunggu...");
          break;
      }
      System.out.println("");
      System.out.println("Apakah anda ingin melanjutkan operasi?(0/1)");
      ch1 = sc.nextInt();
    } while (ch1 == 1);
  }
}
