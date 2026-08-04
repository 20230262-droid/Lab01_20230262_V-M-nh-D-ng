package vn.edu.eaut.lab1;


public class So {


    // Bài 1: Tính tổng số chẵn
    public static int tongChanDenN(int n){

        int tong = 0;


        for(int i = 2; i <= n; i += 2){

            tong += i;

        }


        return tong;
    }




    // Bài 2: Tính tổng nghịch đảo
    public static double tongNghichDao(int n){

        double tong = 0;


        for(int i = 1; i <= n; i++){

            tong += 1.0 / i;

        }


        return tong;
    }





    // Bài 3: Kiểm tra số nguyên tố
    public static boolean laSoNguyenTo(int n){


        if(n < 2){

            return false;

        }


        for(int i = 2; i <= Math.sqrt(n); i++){


            if(n % i == 0){

                return false;

            }

        }


        return true;

    }






    // Bài 4: Phân loại tam giác
    public static String loaiTamGiac(double a, double b, double c){


        // Kiểm tra điều kiện tam giác

        if(a <= 0 || b <= 0 || c <= 0){

            return "Khong phai tam giac";

        }


        if(a+b<=c || a+c<=b || b+c<=a){

            return "Khong phai tam giac";

        }



        // Tam giác đều

        if(a==b && b==c){

            return "Tam giac deu";

        }



        // Tam giác vuông

        if(a*a+b*b==c*c ||
                a*a+c*c==b*b ||
                b*b+c*c==a*a){

            return "Tam giac vuong";

        }



        // Tam giác cân

        if(a==b || a==c || b==c){

            return "Tam giac can";

        }



        return "Tam giac thuong";

    }






    // Bài 5: Fibonacci

    public static String dayFibonacci(int n){


        StringBuilder kq = new StringBuilder();


        long a = 0;

        long b = 1;



        for(int i=1;i<=n;i++){


            kq.append(a);



            if(i<n){

                kq.append(" ");

            }



            long c = a+b;


            a = b;

            b = c;


        }


        return kq.toString();

    }


}