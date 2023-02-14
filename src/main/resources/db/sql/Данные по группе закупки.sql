select min((hai1.duration_ + hai2.duration_)/1000) as min_duration
	  ,avg((hai1.duration_ + hai2.duration_)/1000) as avg_duration
	  ,max((hai1.duration_ + hai2.duration_)/1000) as max_duration
from dict_order ord
inner join act_hi_procinst hpi on hpi.business_key_ = ord.code
inner join act_hi_actinst hai1 on hai1.proc_inst_id_ = hpi.id_
							 and hai1.act_type_ = 'userTask'
							 and hai1.act_id_ = 'purchaseMaterialTask'
inner join act_hi_actinst hai2 on hai2.proc_inst_id_ = hpi.id_
							 and hai2.act_type_ = 'userTask'
							 and hai2.act_id_ = 'deliveryMaterialTask'
where ord.code = '2023-02-14-33'