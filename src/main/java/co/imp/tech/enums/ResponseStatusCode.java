package co.imp.tech.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseStatusCode {
    SUCCESS("00000", 200, "Success", "Success"),
    //Response Error
    E_NOT_PROCESS("80001", 400, "Permintaan tidak dapat diproses", "Kami akan melakukan pengecekan terlebih dahulu. Silahkan kembali beberapa saat lagi"),
    E_TRANSACTION_NOT_FOUND("80002", 404, "Transaksi tidak ditemukan", "Kami sedang mengecek ulang data"),
    E_REQUEST_FAILED("80003", 400, "Permintaan gagal", "Gagal untuk membaca data"),
    //Business Error
    B_REQUEST_FAILED("40001", 400, "Permintaan gagal", "Validasi data tidak berhasil"),
    B_FAILED_SAVE("40002", 400, "Data gagal disimpan", "Validasi data tidak berhasil"),
    B_DATA_IST_UPDATED("40003", 400, "Tidak ada pengikinian data", "Anda belum melakukan perubahan data");


    private final String code;
    private final int httpCode;
    private final String title;
    private final String desc;
}
