package share.book.domain.infras.util;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShortUuidUtils {

  public static String generate() {
    return compress(UUID.randomUUID());
  }

  public static String compress(UUID uuid) {
    // 確保するデータ量
    ByteBuffer buf = ByteBuffer.allocate(Long.BYTES * 2);
    // このUUIDの128ビット値の最上位64ビットを返す
    buf.putLong(uuid.getMostSignificantBits());
    // このUUIDの128ビット値の最下位64ビットを返す
    buf.putLong(uuid.getLeastSignificantBits());
    // バイト配列を返す
    byte[] array = buf.array();
    // base64へEnCode（24byte）
    String b64str = Base64.getUrlEncoder().encodeToString(array);
    // 末尾の"=="を削除（22byte）
    return b64str.substring(0, b64str.length() - 2);
  }

  public static UUID decompress(String compressedUuid) {
    ByteBuffer buf = ByteBuffer.wrap(Base64.getUrlDecoder().decode(compressedUuid + "=="));
    return new UUID(buf.getLong(), buf.getLong());
  }
}
