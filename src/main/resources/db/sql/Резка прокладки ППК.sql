select hai.act_name_
	  ,min(hai.duration_/1000) as min_duration
	  ,avg(hai.duration_/1000) as avg_duration
	  ,max(hai.duration_/1000) as max_duration
from dict_order ord
inner join act_hi_procinst hpi on hpi.business_key_ = ord.code
inner join act_hi_actinst hai on hai.proc_inst_id_ = hpi.id_
							 and hai.act_type_ = 'userTask'
							 and hai.act_id_ = 'makeSpacerTask'
where ord.code = '2023-02-14-33'
group by hai.act_name_