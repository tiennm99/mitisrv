package io.github.tiennm99.mitisrv.demo.serialize;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.tiennm99.mitisrv.demo.serialize.entity.Packet;
import io.github.tiennm99.mitisrv.demo.serialize.entity.req.LoginRequest;
import io.github.tiennm99.mitisrv.demo.serialize.entity.res.ErrorResponse;
import io.github.tiennm99.mitisrv.demo.serialize.entity.res.ErrorResponse.Error;
import org.junit.jupiter.api.Test;

class PacketSerializerTest {

  private static final Gson gson = new GsonBuilder()
      .registerTypeHierarchyAdapter(Packet.class, PacketSerializer.getInstance()).create();

  @Test
  void testSerializeLoginRequest() {
    var packet = new LoginRequest("username", "password");
    var serialized = gson.toJson(packet);
    System.out.println(serialized);
    var deserialized = gson.fromJson(serialized, Packet.class);
    assertEquals(packet, deserialized);
  }

  @Test
  void testSerializeErrorResponse() {
    var packet = new ErrorResponse(Error.PASSWORD_INCORRECT);
    var serialized = gson.toJson(packet);
    System.out.println(serialized);
    var deserialized = gson.fromJson(serialized, Packet.class);
    assertEquals(packet, deserialized);
  }
}
