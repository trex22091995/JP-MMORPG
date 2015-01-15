varying vec3 vertNormal;
varying vec4 vertColor;
uniform sampler2D tex;

void main(){
	vec4 texel = texture2D(tex,gl_TexCoord[0].st);
	texel.r=texel.r*vertColor.r;
	texel.g=texel.g*vertColor.g;
	texel.b=texel.b*vertColor.b;
	texel.a=texel.a*vertColor.a;
	gl_FragColor = vec4(texel.rgb*(vertNormal.z*0.8+0.2),texel.a);
	
}