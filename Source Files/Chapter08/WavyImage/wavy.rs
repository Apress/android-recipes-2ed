#pragma version(1)
#pragma rs java_package_name(ca.tutortutor.wavyimage)

rs_allocation in;
rs_allocation out;
rs_script script;

int height;

void root(const uchar4* v_in, uchar4* v_out, const void* usrData, uint32_t x, 
          uint32_t y)
{
   float scaledy = y/(float) height;
   *v_out = *(uchar4*) rsGetElementAt(in, x, (uint32_t) ((scaledy+
                                      sin(scaledy*100)*0.03)*height));
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