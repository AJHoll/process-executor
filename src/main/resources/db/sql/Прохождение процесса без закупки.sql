select avg(hpi.duration_)/1000 as avg_duration
from dict_order ord
inner join act_hi_procinst hpi on hpi.business_key_ = ord.code
where ord.code = '2023-02-14-33'
  and not exists(select 1
  		 	     from act_hi_actinst hai_in
  		         where hai_in.proc_inst_id_ = hpi.id_
  		           and hai_in.act_type_ = 'userTask'
  		           and hai_in.act_id_ = 'purchaseMaterialTask')