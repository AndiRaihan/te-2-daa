# Tugas Eksperimen 2 DAA
## Greedy vs Branch and Bound (Set Covering Problem)

Nama: Andi Muhamad Dzaky Raihan <br>
NPM: 2106631412 <br>
Kode Asdos: 3 <br>

Perlu diperhatikan, data yang dihasilkan **berasal dari kode python**, di file output_python.txt, bukan kode python. Selain itu, ada sedikit modifikasi di Branch and Bound sehingga bestSubset diinisiasi di awal.

Berikut spesifikasi untuk data small, medium, dan large:
* Small: 20 elemen di universe, jumlah subset 2 - 5, masing-masing subset berukuran (jumlah elemen universe // jumlah subset) * 2 -  (jumlah elemen universe // jumlah subset) * 4. Pada data yang digunakan, jumlah subset = 4.
* Medium: 200 elemen di universe, jumlah subset 20 - 80, masing-masing subset berukuran (jumlah elemen universe // jumlah subset) -  (jumlah elemen universe // jumlah subset) * 2. Pada data yang digunakan, jumlah subset = 72.
* Large: 2000 elemen di universe, jumlah subset 160 - 320, masing-masing subset berukuran (jumlah elemen universe // jumlah subset) // 2 -  (jumlah elemen universe // jumlah subset). Pada data yang digunakan, jumlah subset = 466.

Note: Apabila subset yang dibuat secara acak tidak meng-cover universe, maka akan ditambahkan subset secara acak dari universe yang belum di-cover dengan ukuran yang sesuai (dari rumus yang digunakan di awal) hingga universe ter-cover. Hal ini dilakukan untuk memastikan bahwa setiap universe ter-cover.

Saya mengenerate data seperti demikian untuk melihat relasi antara jumlah elemen universe, jumlah subset, dan ukuran subset dengan waktu eksekusi dari algoritma Greedy dan Branch and Bound. Pada data kecil, saya buat jumlah subset sedikit tapi masing-masing subset meng-cover persentase yang besar dari universe. Pada data medium, saya buat jumlah subset lebih banyak tapi masing-masing subset meng-cover persentase yang lebih kecil dari universe. Pada data large, saya buat jumlah subset lebih banyak lagi dan masing-masing subset meng-cover persentase yang kecil lagi dari universe. Perlu diperhatikan, pertumbuhan jumlah subset, jumlah elemen per subset, dan jumlah elemen tidak sama. Hal ini dilakukan untuk melihat bagaimana algoritma Greedy dan Branch and Bound berperilaku pada kasus yang berbeda-beda serta bagaimana pertumbuhannya.

### Referensi:
* Rubbi, A. (2019, Desember 14). Set-Cover-problem-solution-Python. Github. https://github.com/AndreaRubbi/Set-Cover-problem-solution-Python/