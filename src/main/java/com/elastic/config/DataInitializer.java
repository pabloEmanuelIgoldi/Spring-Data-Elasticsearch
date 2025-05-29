package com.elastic.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.elastic.dto.ProductoRequestDTO;
import com.elastic.service.producto.IProductoService;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private IProductoService productoService;

    @Override
    public void run(String... args) throws Exception {
        if (productoService.getAll().isEmpty()) {
            inicializarDatos();
        }
    }

    private void inicializarDatos() {
        List<ProductoRequestDTO> productos = Arrays.asList(
        	new ProductoRequestDTO("Laptop Dell Inspiron 15", "Laptop Dell Inspiron 15.6 pulgadas, Intel Core i3, 4GB RAM, 256GB SSD", "LAP002", 899000.99, 10, true),
            new ProductoRequestDTO("Laptop HP Pavilion", "Laptop HP Pavilion 15.6 pulgadas, Intel Core i5, 8GB RAM, 256GB SSD", "LAP001", 1234399.99, 10, true),
            new ProductoRequestDTO("Mouse Logitech MX Master", "Mouse inalámbrico ergonómico con múltiples botones programables", "MOU001", 3299.99, 25, true),
            new ProductoRequestDTO("Teclado Mecánico Corsair", "Teclado mecánico RGB con switches Cherry MX Blue", "TEC001", 8149.99, 15, true),
            new ProductoRequestDTO("Monitor Samsung 24\"", "Monitor LED Full HD 24 pulgadas con tecnología IPS", "MON001", 199000.99, 8, true),
            new ProductoRequestDTO("Smartphone Samsung Galaxy", "Smartphone Android 128GB, cámara 64MP, pantalla AMOLED", "CEL001", 699.99, 20, true),
            new ProductoRequestDTO("Tablet iPad Air", "Tablet Apple iPad Air 10.9 pulgadas, chip M1, 64GB", "TAB001", 599.99, 12, true),
            new ProductoRequestDTO("Auriculares Sony WH-1000XM4", "Auriculares inalámbricos con cancelación de ruido", "AUR001", 349.99, 18, true),
            new ProductoRequestDTO("Cámara Canon EOS R6", "Cámara mirrorless full frame 20.1MP con video 4K", "CAM001", 2499.99, 5, true),
            new ProductoRequestDTO("Impresora HP LaserJet", "Impresora láser monocromática con conectividad WiFi", "IMP001", 299.99, 7, true),
            new ProductoRequestDTO("Laptop Acer Aspire 5", "Laptop con procesador Intel Core i5, 8GB RAM, 512GB SSD", "LAPACER001", 499.99, 25, true),
            new ProductoRequestDTO("Auriculares Sony WH-1000XM4", "Auriculares inalámbricos con cancelación de ruido", "AURSONY001", 349.99, 15, true),
            new ProductoRequestDTO("Smartphone Samsung Galaxy S21", "Smartphone 5G, 8GB RAM, 128GB almacenamiento", "MOBSGS21", 799.99, 40, true),
            new ProductoRequestDTO("Tablet Apple iPad Pro 11", "Tablet con chip M1, 128GB almacenamiento, 11 pulgadas", "TABIAPPRO11", 799.99, 10, false),
            new ProductoRequestDTO("Teclado mecánico Razer Huntsman", "Teclado mecánico RGB, switches ópticos", "TECRAZER001", 129.99, 50, true),
            new ProductoRequestDTO("Cámara GoPro Hero 9", "Cámara de acción con pantalla frontal y 5K", "CAMGOPRO9", 399.99, 35, true),
            new ProductoRequestDTO("Monitor Dell UltraSharp 27\"", "Monitor 4K con tecnología IPS", "MONDELUS27", 599.99, 20, true),
            new ProductoRequestDTO("Router TP-Link Archer AX6000", "Router Wi-Fi 6, 8 puertos LAN", "RTPAX6000", 199.99, 45, true),
            new ProductoRequestDTO("Consola PlayStation 5", "Consola de videojuegos, 825GB SSD, 4K", "PS5CON001", 499.99, 10, false),
            new ProductoRequestDTO("Bocina Bluetooth JBL Flip 5", "Bocina portátil resistente al agua", "BOCJBL5", 119.99, 100, true),
            new ProductoRequestDTO("Reloj inteligente Garmin Fenix 6", "Reloj deportivo con GPS, resistente al agua", "RELGARFENIX6", 599.99, 12, true),
            new ProductoRequestDTO("Micrófono USB Blue Yeti", "Micrófono de condensador para streaming y podcasting", "MICBLUEYETI", 129.99, 20, true),
            new ProductoRequestDTO("Placa base ASUS ROG Strix", "Placa base para gaming con chipset Z590", "PLCASUSROG", 179.99, 15, true),
            new ProductoRequestDTO("Cámara digital Nikon D3500", "Cámara réflex digital, 24.2 MP", "CAMNIKON3500", 449.99, 10, false),
            new ProductoRequestDTO("Estabilizador DJI Ronin-S", "Estabilizador para cámaras DSLR y sin espejo", "ESTDJIRONIN", 599.99, 8, true),
            new ProductoRequestDTO("Tarjeta gráfica NVIDIA RTX 3080", "Tarjeta gráfica para gaming y diseño profesional", "TARGNVIDIA3080", 749.99, 5, false),
            new ProductoRequestDTO("Pantalla LG OLED 55\"", "Televisor 4K OLED con webOS", "PANTLGOLED55", 1499.99, 2, true),
            new ProductoRequestDTO("Lentes de sol Ray-Ban Aviator", "Lentes de sol unisex, modelo aviator", "LENTRAYBAN001", 149.99, 60, true),
            new ProductoRequestDTO("Aspiradora robot Roomba 675", "Aspiradora robot con control por app", "ASPIRROO675", 299.99, 30, true),
            new ProductoRequestDTO("Bicicleta de montana Trek Marlin 7", "Bicicleta con cuadro de aluminio, frenos de disco", "BICITREK7", 799.99, 12, true),
            new ProductoRequestDTO("Silla gamer DXRacer Formula", "Silla ergonómica para gaming, respaldo reclinable", "SILDXRFORM", 299.99, 40, true),
            new ProductoRequestDTO("Cargador inalámbrico Anker", "Cargador inalámbrico 10W para iPhone y Android", "CARGANKER", 29.99, 75, true),
            new ProductoRequestDTO("Escáner portátil Epson WorkForce ES-50", "Escáner portátil con alimentación de hojas", "ESCPWFE50", 119.99, 15, true),
            new ProductoRequestDTO("Disco duro externo Seagate 4TB", "Disco duro portátil USB 3.0, 4TB", "DISSEAGATE4TB", 99.99, 50, true),
            new ProductoRequestDTO("Smartwatch Samsung Galaxy Watch 3", "Reloj inteligente con monitor de salud y fitness", "SMAWSAM3", 229.99, 25, true),
            new ProductoRequestDTO("Bicicleta eléctrica Xiaomi", "Bicicleta eléctrica con motor de 250W, autonomía de 30km", "BICELECXM", 599.99, 18, false),
            new ProductoRequestDTO("Teclado Logitech G Pro X", "Teclado mecánico con switches intercambiables", "TECLGPROX", 149.99, 22, true),
            new ProductoRequestDTO("Altavoces Sonos One SL", "Altavoces inalámbricos con sonido estéreo", "ALTSONOS1", 199.99, 50, true),
            new ProductoRequestDTO("Kit de cámaras de seguridad Arlo Pro 3", "Cámaras de seguridad inalámbricas con visión nocturna", "KITARLOPRO3", 499.99, 12, true),
            new ProductoRequestDTO("Silla ergonómica Secretlab Titan", "Silla gaming con soporte lumbar y reclinable", "SILSECTITAN", 449.99, 8, true),
            new ProductoRequestDTO("Lámpara de escritorio Philips Hue", "Lámpara LED inteligente con ajuste de colores", "LAMPLPHHUE", 79.99, 45, true),
            new ProductoRequestDTO("Impresora HP Envy 6020", "Impresora multifuncional con conexión Wi-Fi", "IMPHPENV6020", 89.99, 50, true),
            new ProductoRequestDTO("Estación de carga Anker PowerPort", "Estación de carga con 6 puertos USB", "CARGANKER6", 39.99, 30, true),
            new ProductoRequestDTO("Disco duro interno Kingston 1TB", "Disco duro SSD con velocidad de 500MB/s", "DISKINGSSD1TB", 87779.99, 100, true),
            new ProductoRequestDTO("Cámara de seguridad Nest Cam", "Cámara de seguridad 1080p con notificaciones en tiempo real", "CAMNESTCAM", 129.99, 25, true),
            new ProductoRequestDTO("Auriculares Bose QuietComfort 35", "Auriculares inalámbricos con cancelación de ruido", "AURBOSEQC35", 299.99, 20, true),
            new ProductoRequestDTO("Batería portátil Anker PowerCore", "Batería externa de 10000mAh para dispositivos móviles", "BATANKERPC", 29.99, 75, true),
            new ProductoRequestDTO("Pantalla táctil 10\" Amazon Fire", "Tableta con pantalla táctil y Alexa integrada", "TABAMZFIRE10", 89.99, 40, true),
            new ProductoRequestDTO("Gafas VR Oculus Quest 2", "Gafas de realidad virtual autónomas", "GAFVRQUEST2", 299.99, 10, true),
            new ProductoRequestDTO("Cámara Canon EOS 90D", "Cámara réflex digital con 32.5 MP", "CAMCANEOS90D", 1299.99, 5, false),
            new ProductoRequestDTO("Parlante Bluetooth Bose SoundLink", "Parlante portátil con sonido estéreo de alta calidad", "PARBOSSOUNDK", 149.99, 50, true),
            new ProductoRequestDTO("Teclado inalámbrico Logitech K780", "Teclado inalámbrico con soporte para dispositivos", "TECLLOGK780", 49.99, 40, true),
            new ProductoRequestDTO("Escáner Canon LiDE 300", "Escáner plano con resolución de 2400x2400 ppp", "ESCANONLIDE", 59.99, 30, true),
            new ProductoRequestDTO("Mochila para laptop North Face", "Mochila resistente al agua, compartimentos acolchados", "MOCHNFBACK", 89.99, 25, true),
            new ProductoRequestDTO("Bicicleta eléctrica Rad Power Bikes", "Bicicleta eléctrica plegable con motor de 750W", "BICIELECRAD", 999.99, 12, false),
            new ProductoRequestDTO("Estuche de lentes Ray-Ban", "Estuche de lente rígido para modelos Ray-Ban", "ESTUCERAYBAN", 29.99, 50, true),
            new ProductoRequestDTO("Laptop Lenovo Legion 5", "Laptop gaming con procesador AMD Ryzen 7, 16GB RAM, 512GB SSD, GTX 1660 Ti", "LAPLLEGI5", 1000000.0, 20, true),
            new ProductoRequestDTO("Laptop Dell XPS 13", "Laptop ultradelgada con procesador Intel Core i7, 16GB RAM, 512GB SSD, pantalla 13.4\" 4K", "LAPDELLXPS13", 1150000.0, 10, true),
            new ProductoRequestDTO("Laptop HP Spectre x360", "Laptop convertible 2 en 1 con Intel Core i7, 16GB RAM, 1TB SSD, pantalla táctil 13.3\"", "LAPHPSPECTRE", 1100000.0, 15, true),
            new ProductoRequestDTO("Laptop MacBook Pro 13\" M1", "Laptop Apple con chip M1, 8GB RAM, 512GB SSD, pantalla Retina", "LAPMBP13M1", 1150000.0, 8, true),
            new ProductoRequestDTO("Laptop ASUS ZenBook Flip 14", "Laptop convertible con procesador Intel Core i7, 16GB RAM, 512GB SSD", "LAPZENBOOK14", 1050000.0, 12, true),
            new ProductoRequestDTO("Laptop Acer Predator Helios 300", "Laptop gaming con Intel Core i7, 16GB RAM, 512GB SSD, RTX 3060", "LAPACERPREDE300", 1200000.0, 6, true),
            new ProductoRequestDTO("Laptop MSI Modern 14", "Laptop ultradelgada con Intel Core i5, 8GB RAM, 512GB SSD", "LAPMSIMODERN14", 850000.0, 20, true),
            new ProductoRequestDTO("Laptop HP Envy 13", "Laptop ultradelgada con Intel Core i7, 16GB RAM, 512GB SSD", "LAPHENVY13", 900000.0, 25, true),
            new ProductoRequestDTO("Laptop Dell Inspiron 14 5000", "Laptop con Intel Core i5, 8GB RAM, 512GB SSD, pantalla Full HD 14\"", "LAPDELLINS14", 850000.0, 30, true),
            new ProductoRequestDTO("Laptop Lenovo IdeaPad 3", "Laptop económica con Intel Core i5, 8GB RAM, 512GB SSD, pantalla 15.6\"", "LAPIDEAPAD3", 800000.0, 40, true),
            new ProductoRequestDTO("Laptop Samsung Galaxy Book Pro 360", "Laptop convertible con Intel Core i7, 16GB RAM, 512GB SSD, pantalla 13.3\" AMOLED", "LAPSAMSUNGBOOK", 1100000.0, 5, true),
            new ProductoRequestDTO("Laptop Alienware m15 R4", "Laptop gaming con Intel Core i7, 16GB RAM, 1TB SSD, RTX 3070", "LAPALIENWARE15", 1200000.0, 2, true),
            new ProductoRequestDTO("Laptop Microsoft Surface Laptop 4", "Laptop con Intel Core i7, 16GB RAM, 512GB SSD, pantalla 13.5\" PixelSense", "LAPMSFSURFACE4", 1090000.0, 18, true),
            new ProductoRequestDTO("Laptop ASUS ROG Zephyrus G14", "Laptop gaming con AMD Ryzen 9, 16GB RAM, 1TB SSD, RTX 3060", "LAPASUSROG14", 1180000.0, 4, true),
            new ProductoRequestDTO("Laptop HP Pavilion x360", "Laptop convertible 2 en 1 con Intel Core i5, 8GB RAM, 512GB SSD", "LAPHXPAVX360", 870000.0, 35, true),
            new ProductoRequestDTO("Laptop MSI Stealth 15M", "Laptop gaming con Intel Core i7, 16GB RAM, 512GB SSD, RTX 3060", "LAPMSISTEALTH", 1100000.0, 6, true),
            new ProductoRequestDTO("Laptop Dell Latitude 7410", "Laptop empresarial con Intel Core i7, 16GB RAM, 512GB SSD, pantalla 14\" Full HD", "LAPDELLLAT7410", 1000000.0, 12, true),
            new ProductoRequestDTO("Laptop Lenovo ThinkPad X1 Carbon", "Laptop profesional con Intel Core i7, 16GB RAM, 1TB SSD, pantalla 14\" Full HD", "LAPLENOVOX1", 1150000.0, 8, true),
            new ProductoRequestDTO("Laptop Huawei MateBook D 14", "Laptop con AMD Ryzen 5, 8GB RAM, 512GB SSD, pantalla 14\" Full HD", "LAPHUMATEBOOKD14", 950000.0, 30, true),
            new ProductoRequestDTO("Laptop Toshiba Dynabook Tecra A50", "Laptop empresarial con Intel Core i5, 8GB RAM, 512GB SSD, pantalla 15.6\"", "LAPTOSHIBATECRA", 820000.3, 14, true),
            new ProductoRequestDTO("Laptop Razer Blade Stealth 13", "Laptop gaming ultradelgada con Intel Core i7, 16GB RAM, 512GB SSD, GTX 1650 Ti", "LAPRZBLST13", 980000.5, 6, true),
            new ProductoRequestDTO("Monitor LG UltraGear 27GN950", "Monitor gamer 27\" 4K UHD Nano IPS 144Hz G-Sync", "MONLG27GN950", 1150000.00, 10, true),
            new ProductoRequestDTO("Monitor Samsung Odyssey G7 32\"", "Monitor curvo 2K QHD 240Hz 1ms FreeSync G-Sync", "MONSAMG732", 1100000.00, 8, true),
            new ProductoRequestDTO("Monitor ASUS ROG Swift PG279Q", "Monitor gamer 27\" QHD IPS 165Hz G-Sync", "MONASUSPG279Q", 1125000.00, 6, true),
            new ProductoRequestDTO("Monitor Dell UltraSharp U2723QE", "Monitor 27\" 4K USB-C HUB IPS DisplayHDR 400", "MONDELLU2723QE", 950000.00, 12, true),
            new ProductoRequestDTO("Monitor BenQ EX3501R", "Monitor curvo ultrawide 35\" QHD HDR USB-C", "MONBENQEX35", 1050000.00, 5, true),
            new ProductoRequestDTO("Monitor LG 32UN880-B Ergo", "Monitor 32\" 4K UHD IPS con brazo ergonómico", "MONLG32UN880", 900000.00, 14, true),
            new ProductoRequestDTO("Monitor Samsung Smart M8", "Monitor 32\" 4K UHD inteligente con apps y cámara", "MONSAMSMARTM8", 880000.00, 9, true),
            new ProductoRequestDTO("Monitor ViewSonic VP3268a-4K", "Monitor profesional 32\" 4K IPS HDR10", "MONVIEWVP32", 930000.00, 7, true),
            new ProductoRequestDTO("Monitor AOC Agon AG273QZ", "Monitor gamer 27\" QHD 240Hz FreeSync Premium", "MONAOC273QZ", 970000.00, 11, true),
            new ProductoRequestDTO("Monitor ASUS ProArt PA32UCX", "Monitor profesional 32\" 4K HDR Thunderbolt 3", "MONASUSPA32", 1200000.00, 3, true),
            new ProductoRequestDTO("Monitor MSI Optix MAG274QRF-QD", "Monitor 27\" QHD Rapid IPS 165Hz Gaming", "MONMSI274QRF", 860000.00, 20, true),
            new ProductoRequestDTO("Monitor Gigabyte M32U", "Monitor 32\" 4K 144Hz IPS para gaming y creación", "MONGIGAM32U", 1180000.00, 6, true),
            new ProductoRequestDTO("Monitor LG 34WN80C-B", "Monitor curvo ultrawide 34\" IPS USB-C", "MONLG34WN80C", 910000.00, 13, true),
            new ProductoRequestDTO("Monitor Philips 346E2CUAE", "Monitor ultrawide 34\" QHD con USB-C", "MONPHI34CUAE", 890000.00, 16, true),
            new ProductoRequestDTO("Monitor Lenovo ThinkVision P32p-20", "Monitor 32\" 4K UHD IPS con USB-C", "MONLENP32P", 920000.00, 10, true),
            new ProductoRequestDTO("Monitor Alienware AW3821DW", "Monitor curvo 37.5\" WQHD+ 144Hz NVIDIA G-Sync", "MONALIENAW38", 1190000.00, 4, true),
            new ProductoRequestDTO("Monitor Dell S3221QS", "Monitor curvo 32\" 4K UHD FreeSync", "MONDELLS3221QS", 870000.00, 18, true),
            new ProductoRequestDTO("Monitor Samsung S80A", "Monitor 32\" 4K UHD IPS con HDR10", "MONSAMS80A", 800000.00, 15, true),
            new ProductoRequestDTO("Monitor HP Z27k G3", "Monitor 27\" 4K USB-C DisplayHDR 400", "MONHPZ27KG3", 970000.00, 9, true),
            new ProductoRequestDTO("Monitor ASUS TUF Gaming VG28UQL1A", "Monitor gamer 28\" 4K 144Hz HDMI 2.1", "MONASUSTUFVG28", 1160000.00, 5, true),

            new ProductoRequestDTO("Disco Duro Externo WD", "Disco duro externo portable 2TB USB 3.0", "HDD001", 45679.99, 30, true)
        );

        for (ProductoRequestDTO producto : productos) {
            productoService.guardar(producto);
        }

        log.info("Datos de prueba inicializados correctamente");
    }
}