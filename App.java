package vn.edu.eaut.lab1;


import java.util.Scanner;



public class App {


    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);



        int chon;



        do{


            System.out.println("==============================");

            System.out.println(" LAB 1 - JAVA SE CONSOLE ");

            System.out.println("==============================");


            System.out.println("1. Tinh tong so chan");

            System.out.println("2. Tinh tong nghich dao");

            System.out.println("3. Kiem tra so nguyen to");

            System.out.println("4. Phan loai tam giac");

            System.out.println("5. Day Fibonacci");

            System.out.println("0. Thoat");



            System.out.print("Nhap lua chon: ");

            chon = sc.nextInt();



            switch(chon){



                case 1:


                    System.out.print("Nhap n: ");

                    int n = sc.nextInt();


                    System.out.println(
                            "Tong = " + So.tongChanDenN(n)
                    );


                    break;





                case 2:


                    System.out.print("Nhap n: ");

                    n = sc.nextInt();


                    System.out.println(
                            "Tong = " + So.tongNghichDao(n)
                    );


                    break;





                case 3:


                    System.out.print("Nhap n: ");

                    n = sc.nextInt();



                    if(So.laSoNguyenTo(n)){


                        System.out.println(
                                n+" la so nguyen to"
                        );


                    }

                    else{


                        System.out.println(
                                n+" khong phai so nguyen to"
                        );


                    }


                    break;







                case 4:


                    System.out.print("Nhap a: ");

                    double a = sc.nextDouble();


                    System.out.print("Nhap b: ");

                    double b = sc.nextDouble();


                    System.out.print("Nhap c: ");

                    double c = sc.nextDouble();



                    System.out.println(
                            So.loaiTamGiac(a,b,c)
                    );


                    break;







                case 5:


                    System.out.print("Nhap n: ");

                    n = sc.nextInt();



                    System.out.println(
                            So.dayFibonacci(n)
                    );


                    break;





                case 0:


                    System.out.println(
                            "Ket thuc chuong trinh"
                    );


                    break;



                default:


                    System.out.println(
                            "Lua chon khong hop le"
                    );

            }



            System.out.println();



        }while(chon != 0);



        sc.close();


    }


}