# temporallight_mod
 for dependency use download the mod
 
 
 inside the build.gradle put replace anything with [] to what is specified below
 
 repositories {
  flatDir{dirs 'path to mod folder'}
 }
 
 
dependencies {
  implementation fg.deobf('net.tsw.temporallight:[filename without the .jar]:[mod ver]')
}


