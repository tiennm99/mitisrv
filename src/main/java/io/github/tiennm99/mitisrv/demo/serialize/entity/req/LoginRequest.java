package io.github.tiennm99.mitisrv.demo.serialize.entity.req;

import io.github.tiennm99.mitisrv.demo.serialize.entity.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginRequest implements Packet {

  String username;
  String password;
}
