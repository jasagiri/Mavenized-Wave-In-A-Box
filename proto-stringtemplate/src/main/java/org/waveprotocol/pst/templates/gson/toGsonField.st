$if (f.optional)$
if ($source$.has$f.capName$()) {
$endif$

  $if (f.repeated)$
  {
    JsonArray array = new JsonArray();
    for (int i = 0; i < $source$.$f.getter$Size(); i++) {
      $if (f.message)$
        JsonElement elem = $f.javaType$.toGsonHelper($source$.$f.getter$(i), raw, gson);
        // NOTE(kalman): if multistage parsing worked, split point would go here.
        array.add(elem);
      $elseif (f.enum)$
        array.add(new JsonPrimitive($source$.$f.getter$(i).getValue()));
      $elseif (f.blob)$
        array.add(new JsonPrimitive($source$.$f.getter$(i).getData()));
      $elseif (f.long)$
        array.add(GsonUtil.toJson($source$.$f.getter$(i)));
      $else$
        array.add(new JsonPrimitive($source$.$f.getter$(i)));
      $endif$
    }
    $target$.add("$f.number$", array);
  }
  $else$
    $if (f.message)$
    {
      JsonElement elem = $f.javaType$.toGsonHelper($source$.$f.getter$(), raw, gson);
      $target$.add("$f.number$", elem);
    }
    $elseif (f.enum)$
      $target$.add("$f.number$", new JsonPrimitive($source$.$f.getter$().getValue()));
    $elseif (f.blob)$
      $target$.add("$f.number$", new JsonPrimitive($source$.$f.getter$().getData()));
    $elseif (f.long)$
      $target$.add("$f.number$", GsonUtil.toJson($source$.$f.getter$()));
    $else$
      $target$.add("$f.number$", new JsonPrimitive($source$.$f.getter$()));
    $endif$
  $endif$

$if (f.optional)$
}
$endif$
