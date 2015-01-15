varying vec3 vertNormal;
varying vec4 vertColor;

void main(){
	gl_Position = gl_ModelViewProjectionMatrix*gl_Vertex;
	vertNormal = normalize(gl_NormalMatrix * gl_Normal);
	vertColor = gl_Color;
	gl_TexCoord[0] = gl_MultiTexCoord0;
}