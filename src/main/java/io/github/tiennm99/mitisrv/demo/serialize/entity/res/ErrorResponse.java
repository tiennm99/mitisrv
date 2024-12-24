package io.github.tiennm99.mitisrv.demo.serialize.entity.res;

import io.github.tiennm99.mitisrv.demo.serialize.entity.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponse implements Packet {

  Error error;
  public enum Error {
    SUCCESS,
    UNKNOWN,
    USERNAME_NOT_FOUND,
    PASSWORD_INCORRECT,
  }
}
