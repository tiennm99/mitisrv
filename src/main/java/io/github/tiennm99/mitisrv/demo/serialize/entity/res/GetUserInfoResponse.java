package io.github.tiennm99.mitisrv.demo.serialize.entity.res;

import io.github.tiennm99.mitisrv.demo.serialize.entity.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GetUserInfoResponse implements Packet {

  String name;
  String email;
  String phone;
}
