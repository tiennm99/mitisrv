package io.github.tiennm99.mitisrv.demo.serialize;

import io.github.tiennm99.mitisrv.util.gson.AbstractTypeSerializer;
import io.github.tiennm99.mitisrv.demo.serialize.entity.Packet;
import io.github.tiennm99.mitisrv.util.reflection.ReflectionUtil;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PacketSerializer extends AbstractTypeSerializer<Packet> {

  @Getter(lazy = true)
  private static final PacketSerializer instance = new PacketSerializer();

  private static final Map<String, Class<? extends Packet>> classNameToClass =
      ReflectionUtil.getSubTypesOf(Packet.class).stream()
          .collect(Collectors.toMap(Class::getSimpleName, clazz -> clazz));


  @Override
  protected Class<?> getClassForName(String className) {
    return classNameToClass.get(className);
  }

  @Override
  protected String getClassName(Packet object) {
    return object.getClass().getSimpleName();
  }

}
