#pragma version(1)
#pragma rs java_package_name(ca.tutortutor.grayscale)

rs_allocation in;
rs_allocation out;
rs_script script;

const static float3 gsVector = {0.3f, 0.6f, 0.1f};

void root(const uchar4* v_in, uchar4* v_out)
{
   float4 f4 = rsUnpackColor8888(*v_in);
   *v_out = rsPackColorTo8888((float3) dot(f4.rgb, gsVector));
}

void filter()
{
   rsDebug("RS_VERSION = ", RS_VERSION);
#if !defined(RS_VERSION) || (RS_VERSION < 14)
   rsForEach(script, in, out, 0);
#else
   rsForEach(script, in, out);
#endif
}