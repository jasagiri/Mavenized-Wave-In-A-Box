$if (f.optional)$

  @Override
  public boolean has$f.capName$() {
    lazilySwitchToProto();
    return proto.has$f.capName$();
  }

  @Override
  public void clear$f.capName$() {
    lazilySwitchToProtoBuilder();
    protoBuilder.clear$f.capName$();
  }

$endif$

$if (f.repeated)$

  @Override
  public List<$f.boxedJavaType$> $f.getter$() {
    lazilySwitchToProto();
    $if (f.message)$
      List<$f.javaType$> list = new ArrayList<$f.javaType$>();
      for (int i = 0; i < $f.getter$Size(); i++) {
        $f.javaType$ message = new $f.javaType$(proto.$f.getter$(i));
        list.add(message);
      }
      return list;
    $elseif (f.enum)$
      List<$f.javaType$> list = new ArrayList<$f.javaType$>();
      for (int i = 0; i < $f.getter$Size(); i++) {
        $f.javaType$ enumValue = ProtoEnums.valOf(proto.$f.getter$(i).getNumber(), $f.javaType$.values());
        list.add(enumValue);
      }
      return list;
    $elseif (f.blob)$
      List<$f.javaType$> list = new ArrayList<$f.javaType$>();
      for (int i = 0; i < $f.getter$Size(); i++) {
        $f.javaType$ blob = new Blob(Codec.encode(proto.$f.getter$(i).toByteArray()));
        list.add(blob);
      }
      return list;
    $else$
      return Collections.unmodifiableList(proto.$f.getter$List());
    $endif$
  }

  $if (f.message)$
    @Override
    public void addAll$f.capName$(List<? extends $f.messageType$> values) {
      for ($f.messageType$ message : values) {
        add$f.capName$(message);
      }
    }
  $else$
    @Override
    public void addAll$f.capName$(List<$f.boxedJavaType$> values) {
      $if (f.enum)$
        for ($f.javaType$ enumValue : values) {
          add$f.capName$(enumValue);
        }
      $elseif (f.blob)$
        for ($f.javaType$ blob : values) {
          add$f.capName$(blob);
        }
      $else$
        lazilySwitchToProtoBuilder();
        protoBuilder.addAll$f.capName$(values);
      $endif$
    }
  $endif$

  @Override
  public $f.javaType$ $f.getter$(int n) {
    lazilySwitchToProto();
    $if (f.message)$
      return new $f.javaType$(proto.$f.getter$(n));
    $elseif (f.enum)$
      return toPojoEnum$f.javaType$(proto.$f.getter$(n));
    $elseif (f.blob)$
      return new Blob(Codec.encode(proto.$f.getter$(n).toByteArray()));
    $elseif (f.int52)$
      return Int52.int64to52(proto.$f.getter$(n));
    $else$
      return proto.$f.getter$(n);
    $endif$
  }

  $if (f.message)$
    @Override
    public void $f.setter$(int n, $f.messageType$ value) {
      lazilySwitchToProtoBuilder();
      protoBuilder.$f.setter$(n, getOrCreate$f.javaType$(value).getPB());
    }
  $else$
    @Override
    public void $f.setter$(int n, $f.javaType$ value) {
      lazilySwitchToProtoBuilder();
      $if (f.enum)$
        protoBuilder.$f.setter$(n, toProtoEnum$f.javaType$(value));
      $elseif (f.blob)$
        protoBuilder.$f.setter$(n, ByteString.copyFrom(Codec.decode(value.getData())));
      $else$
        protoBuilder.$f.setter$(n, value);
      $endif$
    }
  $endif$

  @Override
  public int $f.getter$Size() {
    lazilySwitchToProto();
    return proto.$f.getter$Count();
  }

  $if (f.message)$
    @Override
    public void add$f.capName$($f.messageType$ value) {
      lazilySwitchToProtoBuilder();
      protoBuilder.add$f.capName$(getOrCreate$f.javaType$(value).getPB());
    }
  $else$
    @Override
    public void add$f.capName$($f.javaType$ value) {
      lazilySwitchToProtoBuilder();
      $if (f.enum)$
        protoBuilder.add$f.capName$(toProtoEnum$f.javaType$(value));
      $elseif (f.blob)$
        protoBuilder.add$f.capName$(ByteString.copyFrom(Codec.decode(value.getData())));
      $else$
        protoBuilder.add$f.capName$(value);
      $endif$
    }
  $endif$

  @Override
  public void clear$f.capName$() {
    lazilySwitchToProtoBuilder();
    protoBuilder.clear$f.capName$();
  }

$else$

  @Override
  public $f.javaType$ $f.getter$() {
    lazilySwitchToProto();
    $if (f.message)$
      return new $f.javaType$(proto.$f.getter$());
    $elseif (f.enum)$
      return toPojoEnum$f.javaType$(proto.$f.getter$());
    $elseif (f.blob)$
      return new Blob(Codec.encode(proto.$f.getter$().toByteArray()));      
    $else$
      return proto.$f.getter$();
    $endif$
  }

  $if (f.message)$
    @Override
    public void $f.setter$($f.messageType$ value) {
      lazilySwitchToProtoBuilder();
      protoBuilder.clear$f.capName$();
      protoBuilder.$f.setter$(getOrCreate$f.javaType$(value).getPB());
    }
  $else$
    @Override
    public void $f.setter$($f.javaType$ value) {
      lazilySwitchToProtoBuilder();
      $if (f.enum)$
        protoBuilder.$f.setter$(toProtoEnum$f.javaType$(value));
      $elseif (f.blob)$
        protoBuilder.$f.setter$(ByteString.copyFrom(Codec.decode(value.getData())));
      $elseif (f.int52)$
        protoBuilder.$f.setter$(Int52.int52to64(value));
      $else$
        protoBuilder.$f.setter$(value);
      $endif$
    }
  $endif$

$endif$
