-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 02, 2026 at 12:35 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbtokobuku`
--

-- --------------------------------------------------------

--
-- Table structure for table `buku`
--

CREATE TABLE `buku` (
  `kd_buku` varchar(15) NOT NULL,
  `nama_buku` varchar(50) NOT NULL,
  `satuan` varchar(20) NOT NULL,
  `harga` int(20) NOT NULL,
  `stok` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `buku`
--

INSERT INTO `buku` (`kd_buku`, `nama_buku`, `satuan`, `harga`, `stok`) VALUES
('BK001', 'Laskar Pelangi', 'Pcs', 95000, 69),
('BK002', 'Bumi Manusia', 'Ekslembar', 110000, 81),
('BK003', 'Negeri 5 Menara', 'Set', 85000, 58),
('BK004', 'Ayat-Ayat Cinta', 'Pcs', 90000, 94),
('BK005', 'Dilan 1990', 'Pack', 78000, 67),
('BK006', 'Atomic Habits', 'Ekslembar', 145000, 904),
('BK007', 'Rich Dad Poor Dad', 'Pcs', 120000, 73),
('BK008', 'Filosofi Teras', 'Lusin', 98000, 57),
('BK009', 'The Psychology of Money', 'Pcs', 150000, 84),
('BK010', 'The Subtle Art of Not Giving a F*ck', 'Kodi', 135000, 50),
('BK011', 'Harry Potter dan Batu Bertuah', 'Pack', 160000, 98),
('BK012', 'Habibie & Ainun', 'Ekslembar', 100000, 65),
('BK013', 'Pulang', 'Pcs', 105000, 87),
('BK014', 'Cantik Itu Luka', 'Set', 125000, 76),
('BK015', 'Sang Pemimpi', 'Lusin', 92000, 97);

-- --------------------------------------------------------

--
-- Table structure for table `penjual`
--

CREATE TABLE `penjual` (
  `id_transaksi` int(20) NOT NULL,
  `no_faktur` varchar(59) NOT NULL,
  `tanggal` date NOT NULL,
  `kd_buku` varchar(15) NOT NULL,
  `id_user` varchar(8) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `sub_total` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `penjual`
--

INSERT INTO `penjual` (`id_transaksi`, `no_faktur`, `tanggal`, `kd_buku`, `id_user`, `jumlah`, `sub_total`) VALUES
(65, 'NFS00001', '2026-06-30', 'BK001', 'admin', 1, 95000),
(66, 'NFS00001', '2026-06-30', 'BK001', 'admin', 1, 95000),
(67, 'NFS00001', '2026-06-30', 'BK002', 'admin', 1, 110000),
(68, 'NFS00001', '2026-06-30', 'BK004', 'admin', 1, 90000),
(69, 'NFS00001', '2026-06-30', 'BK010', 'admin', 1, 135000),
(70, 'NFS00002', '2026-06-30', 'BK001', 'KD02', 1, 95000),
(71, 'NFS00002', '2026-06-30', 'BK015', 'KD02', 1, 92000),
(72, 'NFS00002', '2026-06-30', 'BK012', 'KD02', 1, 100000),
(73, 'NFS00002', '2026-06-30', 'BK010', 'KD02', 1, 135000),
(74, 'NFS00003', '2026-06-30', 'BK001', 'KD01', 1, 95000),
(75, 'NFS00003', '2026-06-30', 'BK015', 'KD01', 1, 92000),
(76, 'NFS00004', '2026-06-30', 'BK005', 'KD03', 1, 78000),
(77, 'NFS00004', '2026-06-30', 'BK008', 'KD03', 1, 98000),
(78, 'NFS00004', '2026-06-30', 'BK013', 'KD03', 1, 105000),
(79, 'NFS00004', '2026-06-30', 'BK015', 'KD03', 1, 92000),
(80, 'NFS00004', '2026-06-30', 'BK014', 'KD03', 1, 125000),
(81, 'NFS00005', '2026-07-01', 'BK003', 'KD01', 1, 85000),
(82, 'NFS00006', '2026-07-02', 'BK011', 'KD01', 1, 160000),
(83, 'NFS00006', '2026-07-02', 'BK003', 'KD01', 1, 85000);

-- --------------------------------------------------------

--
-- Stand-in structure for view `relasidatatransaksi`
-- (See below for the actual view)
--
CREATE TABLE `relasidatatransaksi` (
`no_faktur` varchar(59)
,`tanggal` date
,`id_user` varchar(8)
,`nama` varchar(25)
,`email` varchar(25)
,`username` varchar(20)
,`akses` varchar(20)
,`kd_buku` varchar(15)
,`nama_buku` varchar(50)
,`satuan` varchar(20)
,`harga` int(20)
,`stok` int(11)
,`jumlah` int(11)
,`sub_total` int(20)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `relasikeranjang`
-- (See below for the actual view)
--
CREATE TABLE `relasikeranjang` (
`no_faktur` varchar(59)
,`tanggal` date
,`id_user` varchar(8)
,`username` varchar(20)
,`akses` varchar(20)
,`kd_buku` varchar(15)
,`nama_buku` varchar(50)
,`harga` int(20)
,`jumlah` int(11)
,`sub_total` int(20)
);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` varchar(8) NOT NULL,
  `nama` varchar(25) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `telepon` varchar(15) NOT NULL,
  `email` varchar(25) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `akses` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `nama`, `alamat`, `telepon`, `email`, `username`, `password`, `akses`) VALUES
('1', '1', '1', '1', '1', '1', '1', 'Admin'),
('KD01', 'admiin', 'SUKARAMAI', '0835', 'admin0@gmail.com', 'admin', 'admin', 'Admin'),
('KD02', 'moses', 'pku 2', '081524261514', 'adrian@gmail.com', 'moses', '02', 'Admin'),
('KD03', 'IKA', 'padau', '082623', 'nauli@gmail.com', 'ika', '03', 'Kasir');

-- --------------------------------------------------------

--
-- Structure for view `relasidatatransaksi`
--
DROP TABLE IF EXISTS `relasidatatransaksi`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `relasidatatransaksi`  AS SELECT `penjual`.`no_faktur` AS `no_faktur`, `penjual`.`tanggal` AS `tanggal`, `user`.`id_user` AS `id_user`, `user`.`nama` AS `nama`, `user`.`email` AS `email`, `user`.`username` AS `username`, `user`.`akses` AS `akses`, `buku`.`kd_buku` AS `kd_buku`, `buku`.`nama_buku` AS `nama_buku`, `buku`.`satuan` AS `satuan`, `buku`.`harga` AS `harga`, `buku`.`stok` AS `stok`, `penjual`.`jumlah` AS `jumlah`, `penjual`.`sub_total` AS `sub_total` FROM ((`penjual` join `user`) join `buku`) WHERE `penjual`.`id_user` = `user`.`id_user` AND `penjual`.`kd_buku` = `buku`.`kd_buku` GROUP BY `penjual`.`no_faktur`, `penjual`.`tanggal`, `penjual`.`id_user`, `penjual`.`kd_buku` ;

-- --------------------------------------------------------

--
-- Structure for view `relasikeranjang`
--
DROP TABLE IF EXISTS `relasikeranjang`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `relasikeranjang`  AS SELECT `penjual`.`no_faktur` AS `no_faktur`, `penjual`.`tanggal` AS `tanggal`, `user`.`id_user` AS `id_user`, `user`.`username` AS `username`, `user`.`akses` AS `akses`, `buku`.`kd_buku` AS `kd_buku`, `buku`.`nama_buku` AS `nama_buku`, `buku`.`harga` AS `harga`, `penjual`.`jumlah` AS `jumlah`, `penjual`.`sub_total` AS `sub_total` FROM ((`penjual` join `user`) join `buku`) WHERE `penjual`.`id_user` = `user`.`id_user` AND `penjual`.`kd_buku` = `buku`.`kd_buku` GROUP BY `penjual`.`no_faktur`, `penjual`.`tanggal`, `penjual`.`id_user`, `penjual`.`kd_buku` ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`kd_buku`);

--
-- Indexes for table `penjual`
--
ALTER TABLE `penjual`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `kd_barang` (`kd_buku`,`id_user`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `penjual`
--
ALTER TABLE `penjual`
  MODIFY `id_transaksi` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=84;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
